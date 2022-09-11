/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record.name;

import glyphreader.enumtypes.LanguageIDsMacintoshEnum;
import glyphreader.enumtypes.PlatformSpecificIDMacintoshEnum;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author jmburu
 */
public class PlatformTypeMacintosh extends PlatformTypeAbstract<PlatformSpecificIDMacintoshEnum, LanguageIDsMacintoshEnum> {
    private PlatformSpecificIDMacintoshEnum encode = null;
    private LanguageIDsMacintoshEnum language = null;
    
    
    @Override
    public void setSpecificEncoding(PlatformSpecificIDMacintoshEnum encode) {
        this.encode = encode;
    }

    @Override
    public PlatformSpecificIDMacintoshEnum getSpecificEncoding() {
        return encode;
    }

    @Override
    public void setSpecificEncoding(int encode) {
        this.encode = PlatformSpecificIDMacintoshEnum.values()[encode];
    }

    @Override
    public void setLanguage(int id) {
        language = LanguageIDsMacintoshEnum.values()[id];
    }

    @Override
    public void setLanguage(LanguageIDsMacintoshEnum languageID) {
        this.language = languageID;
    }

    @Override
    public LanguageIDsMacintoshEnum getLanguage() {
        return language;
    }

    @Override
    public Charset getPlatformCharset() {
        return StandardCharsets.UTF_8;
    }
    
}
