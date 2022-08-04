/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.core.metrics;

import glyphreader.TrueTypeFont;
import glyphreader.core.FBound;
import glyphreader.core.FPoint2d;
import glyphreader.table.HheaTable;

/**
 *
 * @author user
 * 
 * top left is zero
 * 
 * http://freetype.sourceforge.net/freetype2/docs/glyphs/glyphs-3.html
 * http://westonthayer.com/writing/intro-to-font-metrics/
 * 
 * This font metrics is not verified yet
 * 
 * 
 */
public class FHorizontalMetrics 
{
    private final HheaTable hheaTable;
    private final FBound bound;
    
    public FHorizontalMetrics(TrueTypeFont ttf)
    {        
        this.hheaTable = ttf.getTableList().getTable(HheaTable.class);
        this.bound = ttf.getBound();
    }
    
    public double getAscent()
    {
        return hheaTable.ascent;
    }
    
    public double getDescent()
    {
        return hheaTable.descent;
    }
    
    public FPoint2d getOrigin()
    {
        return getPenPosition();
    }
    
    public FPoint2d getPenPosition()
    {
        return new FPoint2d(getBound().xMin - minLeftSideBearing(), getAscent());
    }
    
    public double lineGap()
    {
        return hheaTable.lineGap;
    }
    
    public FBound getBound()
    {
        return bound;
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
        return minLeftSideBearing();
    }
    
    //Right side bearing
    public double bearingY()
    {
        return minRightSideBearing();
    }
    
    //lsb
    public double minLeftSideBearing()
    {
        return hheaTable.minLeftSideBearing;
    }
        
    //rsb
    public double minRightSideBearing()
    {
        //return hheaTable.minRightSideBearing;
        return advanceMaxX() - minLeftSideBearing() - getBound().getWidth();
    }
    
    //Advance width
    public double advanceMaxX()
    {
        return hheaTable.advanceWidthMax;
    }
    
    public double glyphMaxWidth()
    {
        return getBound().getWidth();
    }
    
    public double glyphMaxHeight()
    {
        return getBound().getHeight();
    }
    
    public double glyphXMin()
    {
        return getBound().xMin;
    }
    
    public double glyphXMax()
    {
        return getBound().xMax;
    }
    
    public double glyphYMin()
    {
        return getBound().yMin;
    }
    
    public double glyphYMax()
    {
        return getBound().yMax;
    }
}
