/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record.name;

import glyphreader.enumtypes.LanguageIDsPlatformAbstractEnum;
import glyphreader.enumtypes.NameIDsEnum;
import glyphreader.enumtypes.PlatformSpecificAbstractEnum;
import static glyphreader.record.name.PlatformCache.fixedLengthString;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jmburu
 * @param <S>
 * @param <L>
 */
public abstract class PlatformTypeAbstract<S  extends PlatformSpecificAbstractEnum, L extends LanguageIDsPlatformAbstractEnum> {
    protected Map<NameIDsEnum, String> nameData = new HashMap();
    
    public abstract void setSpecificEncoding(int encode);
    public abstract void setSpecificEncoding(S encode);
    public abstract S getSpecificEncoding();
    
    public abstract void setLanguage(int languageID);
    public abstract void setLanguage(L language);
    public abstract L getLanguage();
    
    public abstract Charset getPlatformCharset();
    
    
    public final void putNameData(int nameID, String name)
    {
        if(nameID >= 256)
        {
            putNameData(NameIDsEnum.FontSpecificName, name);
            return;
        }
        else if(nameID >= 26)
        {
            putNameData(NameIDsEnum.ReservedForFuture, name);
            return;
        }
        putNameData(NameIDsEnum.values()[nameID], name);
    }
    
    public final void putNameData(NameIDsEnum nameID, String name)
    {
        nameData.put(nameID, name);
    }
    
    public final int getNameDataSize()
    {
        return nameData.size();
    }
    
    public final String getNameData(NameIDsEnum nameID)
    {
        return nameData.get(nameID);
    }
    
    @Override
    public String toString()
    {        
        StringBuilder builder = new StringBuilder();
        builder.append(fixedLengthString("Platform specific: ", 22)).append(getSpecificEncoding()).append("\n");
        builder.append(fixedLengthString("Platform language: ", 22)).append(getLanguage()).append("\n");
        for(int i = 0; i<getNameDataSize(); i++)
        {
            NameIDsEnum nameID =  (NameIDsEnum) nameData.keySet().toArray()[i];
            String name = nameData.get(nameID);
            builder.append(fixedLengthString(nameID.toString(), 20)).append(": ").append(name).append("\n");
        }
        return builder.toString();
    }
}
