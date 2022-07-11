/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.TableRecord;
import glyphreader.read.BinaryMapReader;
import glyphreader.FUtility;
import glyphreader.map.NameRecord;
import glyphreader.map.Table;
import java.util.HashMap;

/**
 *
 * @author jmburu
 */
public class NameTable implements Table{
    
    public NameRecord[] nameRecords = null;        
    
    @Override
    public void read(BinaryMapReader file, HashMap<String, TableRecord> tables)
    {
        if(!tables.containsKey("name"))
            throw new UnsupportedOperationException("No name table");
        
        int tableOffset = tables.get("name").offset;
        file.seek(tableOffset);
        
        int format = file.getUint16(); // must be 0
        FUtility.assertCheck(format == 0, "must be zero");
        
        int count = file.getUint16();
        nameRecords = new NameRecord[count];
        
        int stringOffset = file.getUint16();
        
        for (int i = 0; i < count; i++) 
        {
            NameRecord record = new NameRecord();
            record.platformID = file.getUint16();
            record.platformSpecificID = file.getUint16();
            record.languageID = file.getUint16();
            record.nameID = file.getUint16();
            record.length = file.getUint16();
            record.offset = file.getUint16();
            int old = file.seek(tableOffset + stringOffset + record.offset);
            String name;
            if (record.platformID == 0 || record.platformID == 3) {
                name = file.getUnicodeString(record.length);
            } else {
                name = file.getString(record.length);
            }
            record.name = name;
            
            nameRecords[i] = record;            
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
        
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("table name: ").append("\n");
        for(NameRecord record : nameRecords)
        {
            builder.append(record).append("\n");
        }
        return builder.toString().trim();
    } 
}
