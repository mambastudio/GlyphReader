/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record.name;

import glyphreader.enumtypes.LanguageIDsPlatformAbstractEnum;
import glyphreader.enumtypes.LanguageIDsUnicodeEnum;
import glyphreader.enumtypes.PlatformSpecificIDUnicodeEnum;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author jmburu
 */
public class PlatformTypeUnicode extends PlatformTypeAbstract<PlatformSpecificIDUnicodeEnum, LanguageIDsUnicodeEnum>{
    private PlatformSpecificIDUnicodeEnum encode = null;
    private LanguageIDsUnicodeEnum language = null;
    @Override
    public void setSpecificEncoding(PlatformSpecificIDUnicodeEnum encode) {
        this.encode = encode;
    }

    @Override
    public PlatformSpecificIDUnicodeEnum getSpecificEncoding() {
        return encode;
    }

    @Override
    public void setSpecificEncoding(int encode) {
        this.encode = PlatformSpecificIDUnicodeEnum.values()[encode];
    }

    @Override
    public void setLanguage(int id) {
        language = LanguageIDsUnicodeEnum.NONE;
    }

    @Override
    public void setLanguage(LanguageIDsUnicodeEnum languageID) {
        this.language = languageID;
    }

    @Override
    public LanguageIDsUnicodeEnum getLanguage() {
        return language;
    }

    @Override
    public Charset getPlatformCharset() {
        return StandardCharsets.UTF_16BE;
    }
    
}
