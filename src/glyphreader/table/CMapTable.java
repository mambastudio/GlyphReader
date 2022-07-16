/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.AbstractTable;
import glyphreader.map.TableRecord;
import glyphreader.read.BinaryMapReader;
import glyphreader.map.CMap;
import static glyphreader.map.Table.TableType.CMAP;
import glyphreader.map.TableList;
import glyphreader.map.TrueTypeCmap0;
import glyphreader.map.TrueTypeCmap4;
import java.util.ArrayList;

/**
 *
 * @author jmburu
 */
public class CMapTable extends AbstractTable{
    public ArrayList<CMap> cmaps;
    
    public int version = 0; //Table version number (0).
    public int numberSubtables = 0; //Number of encoding tables that follow.
        
    public CMapTable(TableRecord record)
    {
        super(record);
        this.cmaps = new ArrayList<>();        
    }
    
    @Override
    public boolean read(BinaryMapReader file, TableList tables)
    {
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        version = file.getUint16(); // must be 0
        numberSubtables = file.getUint16();

        // tables must be sorted by platform id and then platform specific
        // encoding.
        for (int i = 0; i < numberSubtables; i++) {
            // platforms are: 
            // 0 - Unicode -- use specific id 6 for full coverage. 0/4 common.
            // 1 - MAcintosh (Discouraged)
            // 2 - reserved
            // 3 - Microsoft
            int platformID = file.getUint16();
            int platformSpecificID = file.getUint16();
            int offset = file.getUint32();
            //System.out.format("CMap platformid = %s specificid = %s offset = %s \n", platformID,
                //platformSpecificID, offset);
            if (platformID == 3 && (platformSpecificID <= 1)) {
                this.readCmap(file, tableOffset + offset);
            }
        }

        // use format 0 table preferably.
        //this.cmaps.sort(function(a, b) {
        //    return a.format - b.format;
        //});
        
        return true;
    }
    
    private void readCmap(BinaryMapReader file, int offset) {
        int oldPos = file.seek(offset);
        
        int format = file.getUint16();
        int length = file.getUint16();
        int language = file.getUint16();
        CMap cmap = null;

        //System.out.format(" Cmap format %s length %s \n", format, length);
        if (format == 0) {
            cmap = new TrueTypeCmap0(file, length);
        } else if (format == 4) {
            cmap = new TrueTypeCmap4(file, length);
        }

        if (cmap != null) {
            this.cmaps.add(cmap);
        }

        file.seek(oldPos);
    }

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return CMAP;
    }

}
