/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.read.BinaryMapReader;
import glyphreader.read.BinaryReader;

/**
 *
 * @author user
 */
public class TableDirectory {
    public int sfntVersion = 0;
    public int numTables = 0;
    public int searchRange = 0;
    public int entrySelector = 0;
    public int rangeShift = 0;
    
    public BinaryReader file;
    
    public TableDirectory(BinaryReader file)
    {
        this.file = file;
        init();
    }
    
    public BinaryReader getFile()
    {
        return file;
    }
    
    public final void init()
    {
        file.seek(0);
        sfntVersion = file.getUint32();
        numTables = file.getUint16();        
        searchRange = file.getUint16();
        entrySelector = file.getUint16();
        rangeShift = file.getUint16();      
        
        
    }
}
