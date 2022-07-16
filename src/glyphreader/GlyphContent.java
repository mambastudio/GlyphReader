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
import glyphreader.map.Table;
import glyphreader.table.CMapTable;
import glyphreader.table.HeadTable;
import glyphreader.table.KernTable;
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
        return ttf.getBound();
    }
    
    public double getUnitsPerEm()
    {
        return ttf.getTableList().getTable(HeadTable.class).unitsPerEm;
    }
    
    public Kern0Table getKern0Table(int index)
    {
        return ttf.getTableList().getTable(KernTable.class).kern.get(index);
    }
    
    public int getKernSize()
    {        
        if(ttf.getTableList().containsTable(Table.TableType.KERN))
            return ttf.getTableList().getTable(KernTable.class).kern.size();
        else
            return 0;
    }
    
    public CMap getCMap(int index)
    {
        return ttf.getTableList().getTable(CMapTable.class).cmaps.get(index);
    }
    
    public int getCMapSize()
    {
        return ttf.getTableList().getTable(CMapTable.class).cmaps.size();
    }
    
    public FPoint2i getHorizontalMetrics(int glyphIndex) 
    {
        return ttf.getHorizontalMetrics(glyphIndex);
    }
}
