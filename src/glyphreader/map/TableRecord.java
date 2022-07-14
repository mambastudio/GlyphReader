/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

/**
 *
 * @author jmburu
 * @param <T>
 */
public class TableRecord<T extends Table> {    
    public int checksum;
    public int offset;
    public int length;
    
    private T table;

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Table: ").append("\n");
        builder.append("checksum ").append(checksum).append("\n");
        builder.append("offset   ").append(offset).append("\n");
        builder.append("length   ").append(length).append("\n");
        return builder.toString();
    }
    
    public void setTable(T t)
    {
        this.table = t;
    }
    
    public T getTable(Class<T> clazz)
    {
        if(table == null)
            return null;
        if(clazz.isAssignableFrom(table.getClass()))
            return (T)table;
        else
            throw new UnsupportedOperationException("Not assignable " +clazz.getSimpleName());
    }
}
