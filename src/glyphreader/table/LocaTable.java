/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.AbstractTable;
import static glyphreader.map.Table.TableType.LOCA;
import glyphreader.map.TableList;
import glyphreader.map.TableRecord;
import glyphreader.read.BinaryMapReader;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class LocaTable extends AbstractTable {
    
    ArrayList<Integer> glyphOffsets = new ArrayList();
    
    boolean shortVersion = false;
    
    public LocaTable(TableRecord record)
    {
        super(record);
    }

    // Parse the `loca` table. This table stores the offsets to the locations of the glyphs in the font,
    // relative to the beginning of the glyphData table.
    // The number of glyphs stored in the `loca` table is specified in the `maxp` table (under numGlyphs)
    // The loca table has two versions: a short version where offsets are stored as uShorts, and a long
    // version where offsets are stored as uLongs. The `head` table specifies which version to use
    // (under indexToLocFormat).
    @Override
    public boolean read(BinaryMapReader file, TableList tables) {
        
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        //read maxp table if not read
        if(!tables.getTable(MaxpTable.class).isRead())
            tables.getTable(MaxpTable.class).parse(file, tables);
        
        //read head table if not read
        if(!tables.getTable(HeadTable.class).isRead())
            tables.getTable(HeadTable.class).parse(file, tables);
        
        shortVersion = tables.getTable(HeadTable.class).indexToLocFormat == 0;
                
        for(int index = 0; index<tables.getTable(MaxpTable.class).numGlyphs + 1; index++)
        {
            if(shortVersion)
            {                                
                // The short table version stores the actual offset divided by 2.
                file.seek(record.offset + index * 2);             
                int glyphOffset = file.getUint16() * 2;          
                glyphOffsets.add(glyphOffset + tables.getTableRecord(TableType.GLYF).offset);
            }
            else
            {
                file.seek(record.offset + index * 4);             
                int glyphOffset = file.getUint32();                
                glyphOffsets.add(glyphOffset + tables.getTableRecord(TableType.GLYF).offset);
            }
        }
        return true;
    }
    
    public boolean isShortVersion()
    {
        return shortVersion;
    }
    
    public boolean isLongVersion()
    {
        return !shortVersion;
    }
    
    public int getGlyphOffset(int index) {       
        int offset, next;          
        offset = glyphOffsets.get(index);
        next = glyphOffsets.get(index + 1);
        
        if (offset == next) {  
            // indicates glyph has no outline( eg space)
            return 0;
        }                
        return glyphOffsets.get(index);
    }

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return LOCA;
    }
    
}
