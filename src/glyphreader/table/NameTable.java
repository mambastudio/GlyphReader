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
import static glyphreader.map.Table.TableType.NAME;
import glyphreader.map.TableList;
import glyphreader.read.BinaryReader;
import glyphreader.record.name.PlatformCache;
import java.nio.charset.StandardCharsets;
import glyphreader.record.name.PlatformTypeAbstract;

/**
 *
 * @author jmburu
 * 
 * https://docs.microsoft.com/en-gb/typography/opentype/spec/name
 * 
 */
public class NameTable extends AbstractTable{
        
    private final PlatformCache platformCache;
    
    private int tableOffset = 0;
    private int stringOffset = 0;   
        
    public NameTable(TableRecord record)
    {
        super(record);
        platformCache = new PlatformCache();
    }
    
    @Override
    public boolean read(BinaryReader file, TableList tables)
    {                
        tableOffset = record.offset;
        file.seek(tableOffset);
        
        //version
        int version = file.getUint16(); // must be 0
        FUtility.assertCheck(version == 0, "must be zero");
             
        //number of records to read
        int count = file.getUint16(); //name recourd count
        
        //Offset to start of string storage (from start of table).
        stringOffset = file.getUint16();
                
        //go through and read the string storage
        for (int i = 0; i < count; i++) 
        {
            int platformID = file.getUint16();
            int platformSpecificID = file.getUint16();
            int languageID = file.getUint16();
            int nameID = file.getUint16();
            int length = file.getUint16();
            int offset = file.getUint16();
            
            
            int old = file.seek(tableOffset + stringOffset + offset);
            String name;
            if (platformID == 0 || platformID == 3)
                name = file.getString(length, StandardCharsets.UTF_16);
            else
                name = file.getString(length, StandardCharsets.UTF_8); //macintosh
                                              
            PlatformTypeAbstract platform = platformCache.getPlatformTypeFromID(platformID);
            //first time?
            if(platform == null)
            {
                platformCache.addPlatformType(platformID);
                platform = platformCache.getPlatformTypeFromID(platformID);
                platform.setSpecificEncoding(platformSpecificID);
                platform.setLanguage(languageID);
            }
            //add info            
            platform.putNameData(nameID, name);
           
            
            file.seek(old);            
        }
        return true;
    }
    
    public String getFirstFontFamily()
    {
        return platformCache.getFirstPlatformType().getNameData(NameIDsEnum.FontFamily);
    }
    
    public String getFirstFontSubFamily()
    {
        return platformCache.getFirstPlatformType().getNameData(NameIDsEnum.FontSubfamily);
    }
    
    public int getNumberOfPlatforms()
    {
        return platformCache.getPlatformTypeSize();
    }
    
    public PlatformCache getPlatforms()
    {
        return platformCache;
    }
    
    @Override
    public String toString()
    {
       return platformCache.toString();
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
    
}
