/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record.name;

import glyphreader.enumtypes.LanguageIDsWindowsEnum;
import glyphreader.enumtypes.PlatformSpecificIDWindowsEnum;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author jmburu
 * 
 * All string data for platform 3 must be encoded in UTF-16BE.
 * 
 */
public class PlatformTypeWindows extends PlatformTypeAbstract<PlatformSpecificIDWindowsEnum, LanguageIDsWindowsEnum>{
    private PlatformSpecificIDWindowsEnum encode = null;
    private LanguageIDsWindowsEnum language = null;
    
    
    @Override
    public void setSpecificEncoding(PlatformSpecificIDWindowsEnum encode) {
        this.encode = encode;
    }

    @Override
    public PlatformSpecificIDWindowsEnum getSpecificEncoding() {
        return encode;
    }

    @Override
    public void setSpecificEncoding(int encode) {
        this.encode = PlatformSpecificIDWindowsEnum.values()[encode];
    }

    @Override
    public void setLanguage(int id) {
        language = LanguageIDsWindowsEnum.forCode(id);
    }

    @Override
    public void setLanguage(LanguageIDsWindowsEnum languageID) {
        this.language = languageID;
    }

    @Override
    public LanguageIDsWindowsEnum getLanguage() {
        return language;
    }

    @Override
    public Charset getPlatformCharset() {
        return StandardCharsets.UTF_16;
    }
    
}
