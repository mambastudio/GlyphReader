/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record.name;

import glyphreader.enumtypes.LanguageIDsPlatformAbstractEnum;
import glyphreader.enumtypes.PlatformSpecificIDUnicodeEnum;
import java.nio.charset.Charset;

/**
 *
 * @author jmburu
 */
public class PlatformTypeUnicode extends PlatformTypeAbstract<PlatformSpecificIDUnicodeEnum, LanguageIDsPlatformAbstractEnum>{

    @Override
    public void setSpecificEncoding(PlatformSpecificIDUnicodeEnum encode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PlatformSpecificIDUnicodeEnum getSpecificEncoding() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSpecificEncoding(int encode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLanguage(int encode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLanguage(LanguageIDsPlatformAbstractEnum encode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LanguageIDsPlatformAbstractEnum getLanguage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Charset getPlatformCharset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
