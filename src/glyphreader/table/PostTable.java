/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import static glyphreader.FUtility.standardNames;
import glyphreader.map.AbstractTable;
import static glyphreader.map.Table.TableType.POST;
import glyphreader.map.TableList;
import glyphreader.record.TableRecord;
import glyphreader.read.BinaryReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class PostTable extends AbstractTable{
    public double version = 0; 
    public double italicAngle = 0;
    public int underlinePosition = 0;
    public int underlineThickness = 0;
    public int isFixedPitch = 0;
    public int minMemType42 = 0;
    public int maxMemType42 = 0;
    public int minMemType1 = 0;
    public int maxMemType1 = 0;
    
    //targeting version 2.0   
    public HashMap<String, Integer> glyphNameMap = null;
        
    public PostTable(TableRecord record)
    {
        super(record);
    }
   
    
    @Override
    public boolean read(BinaryReader file, TableList tables) {
               
        int tableOffset = record.offset;
        file.seek(tableOffset);
        this.version            = file.getVersion16Dot16();
        this.italicAngle        = file.getFixed();
        this.underlinePosition  = file.getInt16();
        this.underlineThickness = file.getFword();
        this.isFixedPitch       = file.getUint32();
        this.minMemType42       = file.getUint32();
        this.maxMemType42       = file.getUint32();
        this.minMemType1        = file.getUint32();
        this.maxMemType1        = file.getUint32();
        
        //init glyph name map
        glyphNameMap = new HashMap();
        
        switch (Double.toString(version)) {
            case "1.0":                
                for(int i = 0; i<standardNames.length; i++)
                    glyphNameMap.put(standardNames[i], i);                
                break;
            case "2.0":
                int numGlyphs = file.getUint16();
                //glyph name indices
                int glyphNameIndex[] = new int[numGlyphs];
                for(int i = 0; i<numGlyphs; i++)               
                    glyphNameIndex[i] = file.getUint16();
                
                for (int i = 0; i < numGlyphs; i++) {
                    
                    if (glyphNameIndex[i] >= standardNames.length) {
                        
                        int nameLength = file.getUint8();
                        glyphNameMap.put(file.getString(nameLength, StandardCharsets.UTF_8), i); //Glyph name strings are encoded in ASCII - 8 bit      
                    }
                    else
                        glyphNameMap.put(standardNames[glyphNameIndex[i]], i);                             
                }   break;        
            case "2.5":
                //This is deprecated
                //https://docs.microsoft.com/en-gb/typography/opentype/spec/post
                break;
            default:
                break;
        }  
        
        return true;
    }    
    
    public String[] glyphNames()
    {
        String[] stringNames = new String[glyphNameMap.size()];        
        return glyphNameMap.keySet().toArray(stringNames);
    }
    
    public HashMap<String, Integer> getGlyphNameMap()
    {
        return glyphNameMap;
    }

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return POST;
    }
}
