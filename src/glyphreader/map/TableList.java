/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.map.Table.TableType;
import java.util.Collection;
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
    
    public Table getTable(TableType type)
    {
        return tables.get(type.toString().toLowerCase());
    }
    
    public <T extends Table> T getTable(Class<T> clazz, TableType type)
    {
        if(containsTable(type))
            return (T) tables.get(type.name().toLowerCase());
        return null;
    }
    
    public TableRecord getTableRecord(TableType type)
    {
        return getTable(type).getRecord();
    }
    
    public<T extends Table> boolean containsTable(TableType type)
    {
        return tables.containsKey(type.name().toLowerCase());
    }
    
    public Collection<Table> getTables()
    {
        return tables.values();
    }
}
