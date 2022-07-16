/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.FUtility;
import glyphreader.map.Table.TableType;
import static glyphreader.map.Table.TableType.HEAD;
import glyphreader.read.BinaryMapReader;
import glyphreader.table.MaxpTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author jmburu
 */
public class TableList {
    private final HashMap<String, AbstractTable> tables;
    private final TableDirectory directory;
    private final ArrayList<TableRecord> records;
    
    public TableList(TableDirectory directory)
    {
        this.tables = new HashMap();
        this.directory = directory;
        this.records = new ArrayList();
        initTableRecords();
    }
    
    public final void initTableRecords()
    {
        for(int i = 0 ; i < directory.numTables; i++ ) {            
            BinaryMapReader file = directory.getFile();
            
            //read table name
            String tag = file.getString(4); 
            
            //if it's supported, add it to list
            if(Table.isSupported(tag))
                addTable(Table.generate(tag));
            
            //read table record if supported
            if(Table.isSupported(tag))
            {
                TableRecord record = tables.get(tag.toLowerCase()).getRecord();
                record.checksum = file.getUint32();
                record.offset = file.getUint32();
                record.length = file.getUint32(); 
                records.add(record);
                if(!Table.compare(tag, HEAD))
                    checkIfTableRecordReadCorrect(record);
            }
            else //just read for table record storage
            {
                TableRecord record = new TableRecord(tag);
                record.checksum = file.getUint32();
                record.offset = file.getUint32();
                record.length = file.getUint32(); 
                records.add(record);
                if(!Table.compare(tag, HEAD))
                    checkIfTableRecordReadCorrect(record);
            }
            
        }  
        
        
    }
    
    public void parseTables()
    {
        for(AbstractTable table : getTables())
        {
            if(!table.isRead())
                table.parse(directory.getFile(), this);
        }
    }
    
    private void addTable(AbstractTable table)
    {
        if(table != null)
            tables.put(table.getName().toLowerCase(), table);
    }
    
    public AbstractTable getTable(TableType type)
    {
        return tables.get(type.toString().toLowerCase());
    }
    
    public <T extends AbstractTable> T getTable(Class<T> clazz)
    {
        String name = clazz.getSimpleName().replaceAll("Table", "");
        
        if(tables.containsKey(name.toLowerCase()))
            return (T) tables.get(name.toLowerCase());
        return null;
    }
    
    public TableRecord getTableRecord(TableType type)
    {
        return getTable(type).getRecord();
    }
    
    public<T extends AbstractTable> boolean containsTable(TableType type)
    {
        return tables.containsKey(type.name().toLowerCase());
    }
    
    public Collection<AbstractTable> getTables()
    {
        return tables.values();
    }
    
    public int getGlyphCount()
    {
        return getTable(MaxpTable.class).numGlyphs;
    }
    
    protected void checkIfTableRecordReadCorrect(TableRecord record)
    {
        FUtility.assertCheck(
                calculateTableChecksum(record.offset, record.length) == record.checksum, 
                "problem reading table record");
    }
    
    //to verify if the unsigned values are read correct
    protected int calculateTableChecksum(int offset, int length)
    {
        int old = directory.getFile().seek(offset);
        int sum = 0;
        int nlongs = ((length + 3) / 4);
        while(nlongs>0 ) {
            sum = (sum + directory.getFile().getUint32() & 0xffffffff);
            nlongs--;
        }
        
        directory.getFile().seek(old);
        return sum;
    }
}
