/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

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
    protected TrueTypeFontInfo fontTTF = null;
    
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
            font.fontTTF = info;
            font.size = size;
            return font;
        }        
    }
    
    public static List<String> getFontNames()
    {
        List<TrueTypeFontInfo> ttfInfos = FontCache.getSystemTTFInfo();
        List<String> ttfNames = new ArrayList();
        for(TrueTypeFontInfo info : ttfInfos)
            ttfNames.add(info.getFontFullName());
        return ttfNames;
    }
    
    public static List<FontType> getAllFonts(int size)
    {
        List<TrueTypeFontInfo> ttfInfos = FontCache.getSystemTTFInfo();
        List<FontType> fonts = new ArrayList();
        for(TrueTypeFontInfo info : ttfInfos)
        {
            FontType font = new FontType();
            font.fontTTF = info;
            font.size = size;
            fonts.add(font);
        }
        return fonts;
    }
    
    public String getFamily()
    {
        return fontTTF.getFontFamily();
    }
    
    public String getSubFamily()
    {
        return fontTTF.getFontSubFamily();
    }
    
    public double getSize()
    {
        return size;
    }
    
    public Path getPath()
    {
        return null;
    }
    
    public boolean isOpenType()
    {
        return true;
    }
    
    public TrueTypeFontInfo getTTFInfo()
    {
        return fontTTF;
    }
    
    @Override
    public String toString()
    {
        return fontTTF.getFontFullName();
    }
}
