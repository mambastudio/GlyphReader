/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.glyf.Glyph;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jmburu
 */
public class FontType 
{
    private double size;
    protected TrueTypeFontInfo fontTTFInfo = null;
    protected TrueTypeFont fontTTF = null;
    
    private FontType()
    {
        
    }
    
    public static FontType font(String fullName, double size)
    {
        TrueTypeFontInfo info = FontCache.getTTFInfo(fullName);
        if(info == null)
            return null;
        else
        {
            FontType font = new FontType();
            font.fontTTFInfo = info;
            font.size = size;
            return font;
        }        
    }
    
    public static FontType font(Path path, double size)
    {
        TrueTypeFontInfo info = new TrueTypeFontInfo(path);
        
        FontType font = new FontType();
        font.fontTTFInfo = info;
        font.size = size;
        return font;
    }
    
    public static List<String> getFontSystemNames()
    {
        List<TrueTypeFontInfo> ttfInfos = FontCache.getSystemTTFInfo();
        List<String> ttfNames = new ArrayList();
        for(TrueTypeFontInfo info : ttfInfos)
            ttfNames.add(info.getFontFullName());
        return ttfNames;
    }
    
    public static List<FontType> getAllSystemFonts(int size)
    {
        List<TrueTypeFontInfo> ttfInfos = FontCache.getSystemTTFInfo();
        List<FontType> fonts = new ArrayList();
        for(TrueTypeFontInfo info : ttfInfos)
        {
            FontType font = new FontType();
            font.fontTTFInfo = info;
            font.size = size;
            fonts.add(font);
        }
        return fonts;
    }
    
    public String getFamily()
    {
        return fontTTFInfo.getFontFamily();
    }
    
    public String getSubFamily()
    {
        return fontTTFInfo.getFontSubFamily();
    }
    
    public double getSize()
    {
        return size;
    }
        
    public boolean isOpenType()
    {
        return true;
    }
    
    public TrueTypeFontInfo getTTFInfo()
    {
        return fontTTFInfo;
    }
    
    public Glyph getGlyph(int index)
    {
        if(fontTTF == null)
            fontTTF = fontTTFInfo.getFontTTF();
        return fontTTF.getGlyph(index);
    }
    
    public TrueTypeFont getTrueTypeFont()
    {
        if(fontTTF == null)
            fontTTF = fontTTFInfo.getFontTTF();
        return fontTTF;
    }
    
    @Override
    public String toString()
    {
        return fontTTFInfo.getFontFullName();
    }
}
