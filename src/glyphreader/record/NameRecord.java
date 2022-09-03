/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record;

import glyphreader.enumtypes.NameIDsEnum;
import glyphreader.enumtypes.PlatformIDEnum;
import glyphreader.enumtypes.PlatformSpecificIDUnicodeEnum;
import glyphreader.enumtypes.LanguageIDsWindowsEnum;

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
    
    public String getPlatformName()
    {
        return PlatformIDEnum.forCode(platformID).name();
    }
    
    public String getPlatformSpecificName()
    {
        return PlatformSpecificIDUnicodeEnum.forCode(platformSpecificID).name();
    }
    
    public String getLanguageName()
    {
        return LanguageIDsWindowsEnum.forCode(languageID).name();
    }
    
    public String getNameID()
    {
        return NameIDsEnum.values()[nameID].name();
    }
    
    public String nameValue()
    {
        return name;
    }

    @Override
    public String toString()
    {
        //System.out.println(Long.decode("0x0436"));
        StringBuilder builder = new StringBuilder();
        builder.append(" platformID         ").append(PlatformIDEnum.forCode(platformID)).append("\n");
        builder.append(" platformSpecificID ").append(PlatformSpecificIDUnicodeEnum.forCode(platformSpecificID)).append("\n");
        builder.append(" languageID         ").append(LanguageIDsWindowsEnum.forCode(languageID)).append("\n");
        builder.append(" nameID             ").append(NameIDsEnum.values()[nameID]).append("\n");
        builder.append(" length             ").append(length).append("\n");
        builder.append(" offset             ").append(offset).append("\n");
        builder.append(" name based on ID   ").append(name).append("\n");
        return builder.toString();
    }
}

