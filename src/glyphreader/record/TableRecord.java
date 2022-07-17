/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record;

import glyphreader.map.Table;

/**
 *
 * @author jmburu
 * @param <T>
 */
public class TableRecord<T extends Table> {    
    public int checksum;
    public int offset;
    public int length;
    
    private String name;
    
    public TableRecord(String name)
    {
        if(name == null)
            throw new UnsupportedOperationException("no name");
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Table: ").append("\n");
        builder.append("checksum ").append(checksum).append("\n");
        builder.append("offset   ").append(offset).append("\n");
        builder.append("length   ").append(length).append("\n");
        builder.append("name     ").append(name).append("\n");
        return builder.toString();
    }    
}
