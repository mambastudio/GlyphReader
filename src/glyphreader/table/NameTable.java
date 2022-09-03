/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.record.TableRecord;
import glyphreader.FUtility;
import glyphreader.enumtypes.NameIDsEnum;
import glyphreader.map.AbstractTable;
import glyphreader.record.NameRecord;
import static glyphreader.map.Table.TableType.NAME;
import glyphreader.map.TableList;
import glyphreader.read.BinaryReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author jmburu
 * 
 * https://docs.microsoft.com/en-gb/typography/opentype/spec/name
 * 
 */
public class NameTable extends AbstractTable{
    
   
    
    public NameRecord[] nameRecords = null;   
    
    private int tableOffset = 0;
    private int stringOffset = 0;   
    
    private String fontFamily;
    private String fontSubFamily;
    
    public NameTable(TableRecord record)
    {
        super(record);
    }
    
    @Override
    public boolean read(BinaryReader file, TableList tables)
    {                
        tableOffset = record.offset;
        file.seek(tableOffset);
        
        int format = file.getUint16(); // must be 0
        FUtility.assertCheck(format == 0, "must be zero");
                
        int count = file.getUint16();
        nameRecords = new NameRecord[count];        
        stringOffset = file.getUint16();
                
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
            if (nameRecord.platformID == 0 || nameRecord.platformID == 3)
                name = file.getString(nameRecord.length, StandardCharsets.UTF_16);
            else
                name = file.getString(nameRecord.length, StandardCharsets.UTF_8);
           
            nameRecord.name = name;
            
            nameRecords[i] = nameRecord;            
            file.seek(old);
            
            System.out.println(nameRecord.getPlatformName());

            if(nameRecord.nameID == NameIDsEnum.FontFamily.ordinal())
                fontFamily = nameRecord.name;
            if(nameRecord.nameID == NameIDsEnum.FontSubfamily.ordinal())
                fontSubFamily = nameRecord.name;
        }
        return true;
    }
    
    public String getFontFamily()
    {
        return fontFamily;
    }
    
    public String getFontSubFamily()
    {
        return fontSubFamily;
    }
    
    @Override
    public String toString()
    {
       return summaryInfo();
    } 
    
    public String summaryInfo()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(fixedLengthString("Platform: ", 22)).append(nameRecords[0].getPlatformName()).append("\n");
        builder.append(fixedLengthString("Platform specific: ", 22)).append(nameRecords[0].getPlatformSpecificName()).append("\n");
        builder.append(fixedLengthString("Platform language: ", 22)).append(nameRecords[0].getLanguageName()).append("\n");
        for(NameRecord recordName: nameRecords)
        {
            builder.append(fixedLengthString(recordName.getNameID(), 20)).append(": ").append(recordName.nameValue()).append("\n");
        }
        return builder.toString();
    }
    
    public String nameRecordsInfo()
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
    
    
    //https://stackoverflow.com/questions/13475388/generate-fixed-length-strings-filled-with-whitespaces
    public String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
    
    public class PlatformInfo
    {
        
    }
    
    public class PlatformWindowsInfo
    {
        
    }
    
    public class PlatformMacintoshInfo
    {
        
    }
}
