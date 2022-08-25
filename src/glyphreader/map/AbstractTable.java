/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.record.TableRecord;
import glyphreader.read.BinaryMapReader;
import glyphreader.read.BinaryReader;

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

    public void parse(BinaryReader file, TableList tables)
    {
        int old = file.tell();
        setIsRead(read(file, tables));
        file.seek(old);
        
    }
    
    @Override
    public abstract boolean read(BinaryReader file, TableList tables) ;   
    @Override
    public abstract TableType getType();
}
