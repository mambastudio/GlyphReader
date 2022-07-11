/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

/**
 *
 * @author user
 */
public class NameRecord {
    public int platformID = 0;
    public int platformSpecificID = 0;
    public int languageID = 0;
    public int nameID = 0;
    public int length = 0;
    public int offset = 0;
    public String name = null;

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(" platformID         ").append(platformID).append("\n");
        builder.append(" platformSpecificID ").append(platformSpecificID).append("\n");
        builder.append(" languageID         ").append(languageID).append("\n");
        builder.append(" nameID             ").append(nameID).append("\n");
        builder.append(" length             ").append(length).append("\n");
        builder.append(" offset             ").append(offset).append("\n");
        builder.append(" name               ").append(name).append("\n");
        return builder.toString();
    }
}

