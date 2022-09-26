/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.fonts.notoserif.Resource;
import glyphreader.map.TableDirectory;
import glyphreader.map.TableList;
import glyphreader.read.BinaryBufferReader;
import glyphreader.read.BinaryReader;
import glyphreader.record.name.PlatformTypeAbstract;
import glyphreader.table.NameTable;
import glyphreader.table.PostTable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmburu
 * 
 * for parsing font details
 * 
 */
public class TrueTypeFontInfo {
    public BinaryReader file = null;  
    public TableDirectory directory = null;
    public TableList tables = null;
    
    //default font info
    private NameTable nameTable = null;
    private ArrayList<PlatformTypeAbstract> platformList = null;
        
    //glyph names
    private HashMap<String, Integer> glyphNamesMap ;
       
    public TrueTypeFontInfo(Path path)
    {
        try {
            if(!Files.exists(path))
                throw new UnsupportedOperationException("File does not exist: " +path);
            this.file = new BinaryBufferReader(new FileInputStream(path.toFile()));
            //read the manifest of nameTable
            this.directory = new TableDirectory(file);
            //read nameTable offsetss
            this.tables = new TableList(directory);
            //name table has a summary of font info
            nameTable = tables.getTable(NameTable.class);
            nameTable.parse(file, tables);
            platformList = nameTable.getPlatforms().getAllPlatforms();
            //glyph info
            PostTable postTable = tables.getTable(PostTable.class);
            postTable.parse(file, tables);
            glyphNamesMap = postTable.getGlyphNameMap();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TrueTypeFontInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TrueTypeFontInfo(Class<?> clazz, String fileName)
    {
        this.file = new BinaryBufferReader(Resource.getInputStream(Resource.class, fileName));
        //read the manifest of nameTable
        this.directory = new TableDirectory(file);
        //read nameTable offsetss
        this.tables = new TableList(directory);
        //name table has a summary of font info
        nameTable = tables.getTable(NameTable.class);
        nameTable.parse(file, tables);
        platformList = nameTable.getPlatforms().getAllPlatforms();
        //glyph info
        PostTable postTable = tables.getTable(PostTable.class);
        postTable.parse(file, tables);
        glyphNamesMap = postTable.getGlyphNameMap();
    }
    
    public TrueTypeFont getFontTTF()
    {
        return new TrueTypeFont(file);
    }
    
    public String getFontBasicInfo()
    {        
        return nameTable.toString();
    }
    
    public String[] glyphNamesInfo()
    {
        String[] stringNames = new String[glyphNamesMap.size()];        
        return glyphNamesMap.keySet().toArray(stringNames);        
    }
    
    public String getFontFamily()
    {        
        return nameTable.getFirstFontFamily();
    }
    
    public boolean containsGlyphName(String glyphName)
    {
        return glyphNamesMap.containsKey(glyphName);
    }
    
    public int getIndexOfGlyphName(String glyphName)
    {
        return glyphNamesMap.getOrDefault(glyphName, -1);
    }
    
    public String getFontSubFamily()
    {
        return nameTable.getFirstFontSubFamily();
    }
    
    public String getFontFullName()
    {
        return nameTable.getFullName();
    }

}
