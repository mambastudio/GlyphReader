/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

/**
 *
 * @author jmburu
 */
public class TableRecord {    
    public int checksum;
    public int offset;
    public int length;

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
}
