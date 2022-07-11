/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class GlyphContent 
{
    private final ArrayList<Glyph> glyphList = new ArrayList();
    
    public GlyphContent(TrueTypeFont ttf)
    {
        for(int i = 0; i<ttf.length; i++)
        {
            Glyph glyph = ttf.readGlyph(i);
            glyphList.add(glyph);
        }
    }
    
    public Glyph get(int index)
    {
        return glyphList.get(index);
    }
    
    public int size()
    {
        return glyphList.size();
    }
}
