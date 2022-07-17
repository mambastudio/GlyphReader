/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.record.TableRecord;
import static glyphreader.map.Table.TableType.*;
import glyphreader.read.BinaryMapReader;
import glyphreader.table.*;

/**
 *
 * @author user
 */
public interface Table {
    
    enum TableType{
        CMAP, HEAD, HHEA, HMTX, KERN, MAXP, NAME, GLYF, POST, LOCA, NULL
    };
    
    public static boolean isSupported(String string)
    {
        return generate(string) != null;
    }
    
    public static AbstractTable generate(String name)
    {               
        if(name == null)
            throw new UnsupportedOperationException("table name is null");
        
        TableRecord record = new TableRecord(name);
        
        if(name.equalsIgnoreCase(CMAP.name()))
            return new CMapTable(record);
        else if(name.equalsIgnoreCase(HEAD.name()))
            return new HeadTable(record);
        else if(name.equalsIgnoreCase(HHEA.name()))
            return new HheaTable(record);
        else if(name.equalsIgnoreCase(HMTX.name()))
            return new HmtxTable(record);
        else if(name.equalsIgnoreCase(KERN.name()))
            return new KernTable(record);
        else if(name.equalsIgnoreCase(MAXP.name()))
            return new MaxpTable(record);
        else if(name.equalsIgnoreCase(NAME.name()))
            return new NameTable(record);
        else if(name.equalsIgnoreCase(GLYF.name()))
            return new GlyfTable(record);
        else if(name.equalsIgnoreCase(POST.name()))
            return new PostTable(record);
        else if(name.equalsIgnoreCase(LOCA.name()))
            return new LocaTable(record);
        
        
        return null;
    }
    
    public static boolean compare(String name, TableType type)
    {
        return name.equalsIgnoreCase(type.name());
    }
    
   
    public boolean read(BinaryMapReader file, TableList tables);    
    public TableRecord getRecord();   
    public TableType getType();
    
    default String getName()
    {
        return getRecord().getName();
    }
    
    
}
