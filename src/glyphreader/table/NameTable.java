/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.record.TableRecord;
import glyphreader.read.BinaryMapReader;
import glyphreader.FUtility;
import glyphreader.map.AbstractTable;
import glyphreader.record.NameRecord;
import static glyphreader.map.Table.TableType.NAME;
import glyphreader.map.TableList;

/**
 *
 * @author jmburu
 */
public class NameTable extends AbstractTable{
    
    public NameRecord[] nameRecords = null;   
    
    public NameTable(TableRecord record)
    {
        super(record);
    }
    
    @Override
    public boolean read(BinaryMapReader file, TableList tables)
    {                
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        int format = file.getUint16(); // must be 0
        FUtility.assertCheck(format == 0, "must be zero");
        
        int count = file.getUint16();
        nameRecords = new NameRecord[count];
        
        int stringOffset = file.getUint16();
        
        for (int i = 0; i < count; i++) 
        {
            NameRecord nameRecord = new NameRecord();
            nameRecord.platformID = file.getUint16();
            nameRecord.platformSpecificID = file.getUint16();
            nameRecord.languageID = file.getUint16();
            nameRecord.nameID = file.getUint16();
            nameRecord.length = file.getUint16();
            nameRecord.offset = file.getUint16();
            int old = file.seek(tableOffset + stringOffset + nameRecord.offset);
            String name;
            if (nameRecord.platformID == 0 || nameRecord.platformID == 3) {
                name = file.getUnicodeString(nameRecord.length);
            } else {
                name = file.getString(nameRecord.length);
            }
            nameRecord.name = name;
            
            nameRecords[i] = nameRecord;            
            file.seek(old);

            /*
            switch (nameID) {
                case 1:
                    this.fontFamily = name;
                    break;
                case 2:
                    this.fontSubFamily = name;
                    break;
                case 4:
                    this.fullName = name;
                    break;
                case 6:
                    this.postscriptName = name;
                    break;
            }
            */
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("table name: ").append("\n");
        for(NameRecord nameRecord : nameRecords)
        {
            builder.append(nameRecord).append("\n");
        }
        return builder.toString().trim();
    } 

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return NAME;
    }
}
