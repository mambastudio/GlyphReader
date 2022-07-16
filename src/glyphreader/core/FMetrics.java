/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.core;

import glyphreader.TrueTypeFont;
import glyphreader.table.HheaTable;

/**
 *
 * @author user
 */
public class FMetrics 
{
    private final TrueTypeFont ttf;
    private final HheaTable table;
    
    public FMetrics(TrueTypeFont ttf)
    {
        this.ttf = ttf;
        this.table = null;//ttf.hheaTable;
    }
    
    public double getAscent()
    {
        return table.ascent;
    }
    
    public double getDescent()
    {
        return table.descent;
    }
    
    public double lineGap()
    {
        return table.lineGap;
    }
    
    public FBound getBound()
    {
        return ttf.getBound();
    }
    
    public double internalLeading()
    {
        return getAscent() - getDescent();
    }
    
    public double externalLeading()
    {
        return lineGap();
    }
    
    //Left side bearing or lsb
    public double bearingX()
    {
        return 0;
    }
    
    //Right side bearing
    public double bearingY()
    {
        return 0;
    }
    
    //lsb
    public double leftSideBearing()
    {
        return bearingX();
    }
    
    public double topSideBearing()
    {
        return bearingY();
    }
    
    //rsb
    public double rightSideBearing()
    {
        return advanceX() - leftSideBearing() - getBound().getWidth();
    }
    
    //Advance width
    public double advanceX()
    {
        return 0;
    }
    
    //Advance height
    public double advanceY()
    {
        return 0;
    }
    
    public double glyphWidth()
    {
        return getBound().getWidth();
    }
    
    public double glyphHeight()
    {
        return getBound().getHeight();
    }
}
