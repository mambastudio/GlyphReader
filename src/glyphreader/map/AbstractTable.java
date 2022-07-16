/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.read.BinaryMapReader;

/**
 *
 * @author user
 */
public abstract class AbstractTable implements Table{
    protected TableRecord record;
    private boolean isRead = false;
    
    public AbstractTable(TableRecord record)
    {
        this.record = record;
    }
    
    @Override
    public TableRecord getRecord()
    {
        return record;
    }
    
    public boolean isRead()
    {
        return isRead;
    }
    
    public void setIsRead(boolean isRead)
    {
        this.isRead = isRead;
    }

    public void parse(BinaryMapReader file, TableList tables)
    {
        int old = file.tell();
        setIsRead(read(file, tables));
        file.seek(old);
        
    }
    
    @Override
    public abstract boolean read(BinaryMapReader file, TableList tables) ;   
    @Override
    public abstract TableType getType();
}
