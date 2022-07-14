/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.map.Table.TableType;
import java.util.HashMap;

/**
 *
 * @author jmburu
 */
public class TableList {
    private final HashMap<String, Table> tables;
    
    public TableList()
    {
        tables = new HashMap();
    }
    
    public void addTable(Table table)
    {
        if(table != null)
            tables.put(table.getName(), table);
    }
    
    public <T extends Table> T getTable(Class<T> clazz, TableType type)
    {
        if(containsTable(type))
            return (T) tables.get(type.name().toLowerCase());
        return null;
    }
    
    public<T extends Table> boolean containsTable(TableType type)
    {
        return tables.containsKey(type.name().toLowerCase());
    }
}
