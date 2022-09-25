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
    NameTable nameTable = null;
    ArrayList<PlatformTypeAbstract> platformList = null;
        
    //glyph names
    PostTable postTable = null;
    String[] glyphNames = null;
    
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
            postTable = tables.getTable(PostTable.class);
            postTable.parse(file, tables);
            glyphNames = postTable.glyphNames();
            
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
        postTable = tables.getTable(PostTable.class);
        postTable.parse(file, tables);
        glyphNames = postTable.glyphNames();
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
        return glyphNames;
    }
    
    public String getFontFamily()
    {        
        return nameTable.getFirstFontFamily();
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
