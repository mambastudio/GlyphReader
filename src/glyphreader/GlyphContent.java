/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.core.FBound;
import glyphreader.core.FPoint2i;
import glyphreader.map.CMap;
import glyphreader.map.Kern0Table;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class GlyphContent 
{
    private final TrueTypeFont ttf;
    private final ArrayList<Glyph> glyphList = new ArrayList();
    
    public GlyphContent(TrueTypeFont ttf)
    {
        this.ttf = ttf;
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
    
    public Glyph get(int index, int size)
    {
        return null;
    }
    
    public int size()
    {
        return glyphList.size();
    }
    
    public FBound getGlyphBound()
    {
        return new FBound(ttf.headTable.xMin, ttf.headTable.yMin, ttf.headTable.xMax, ttf.headTable.yMax);
    }
    
    public double getUnitsPerEm()
    {
        return ttf.headTable.unitsPerEm;
    }
    
    public Kern0Table getKern0Table(int index)
    {
        return ttf.kernTable.kern.get(index);
    }
    
    public int getKernSize()
    {
        return ttf.kernTable.kern.size();
    }
    
    public CMap getCMap(int index)
    {
        return ttf.cmapTable.cmaps.get(index);
    }
    
    public int getCMapSize()
    {
        return ttf.cmapTable.cmaps.size();
    }
    
    public FPoint2i getHorizontalMetrics(int glyphIndex) 
    {
        return ttf.getHorizontalMetrics(glyphIndex);
    }
}
