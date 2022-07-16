/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.Glyph;
import glyphreader.map.AbstractTable;
import static glyphreader.map.Table.TableType.GLYF;
import glyphreader.map.TableList;
import glyphreader.map.TableRecord;
import glyphreader.read.BinaryMapReader;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class GlyfTable extends AbstractTable{

    public ArrayList<Glyph> glyphs;
    
    public GlyfTable(TableRecord record)
    {
        super(record);       
    }
    
    @Override
    public boolean read(BinaryMapReader file, TableList tables) {
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        return false;
    }

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return GLYF;
    }
    
}
