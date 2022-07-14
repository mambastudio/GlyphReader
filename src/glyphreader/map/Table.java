/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import static glyphreader.map.Table.TableType.NULL;
import glyphreader.read.BinaryMapReader;
import java.util.HashMap;

/**
 *
 * @author user
 */
public interface Table {
    
    enum TableType{
        CMAP, HEAD, HHEA, HMTX, KERN, MAXP, NAME, POST, NULL
    };
        
    public void read(BinaryMapReader file, TableList tables);    
    public TableRecord getRecord();   
    public TableType getType();
    
    default String getName()
    {
        return getType().name().toLowerCase();
    }
    
    
}
