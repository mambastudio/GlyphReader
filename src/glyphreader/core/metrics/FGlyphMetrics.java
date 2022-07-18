/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.core.metrics;

import glyphreader.Glyph;
import glyphreader.TrueTypeFont;
import glyphreader.core.FBound;
import glyphreader.record.LongHorMetricRecord;

/**
 *
 * @author jmburu
 */
public class FGlyphMetrics {
    Glyph glyph;
    LongHorMetricRecord metrics;
    
    FBound bound;
    
    public FGlyphMetrics(TrueTypeFont ttf, int glyphIndex)
    {
        this.glyph = ttf.getGlyph(glyphIndex);
        this.bound = glyph.bound.copy();
        this.metrics = ttf.getLongHorMetricRecord(glyphIndex);
    }
    
    public FGlyphMetrics(Glyph glyph)
    {
        this.glyph = glyph;
        this.bound = glyph.bound.copy();
        this.metrics = glyph.getLongHorMetricRecord();
    }
    
    public double advanceWidth()
    {
        return metrics.advanceWidth;
    }
    
    public double leftSideBearing()
    {
        return metrics.lsb;
    }
    
    public double xMin()
    {
        return bound.xMin;
    }
    
    public double yMin()
    {
        return bound.yMin;
    }
    
    public double xMax()
    {
        return bound.xMax;
    }
    
    public double yMax()
    {
        return bound.yMax;
    }
    
    public FBound getBound()
    {
        return bound;
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("xMin     ").append(xMin()).append("\n");
        builder.append("xMax     ").append(xMax()).append("\n");
        builder.append("yMin     ").append(yMin()).append("\n");        
        builder.append("yMax     ").append(yMax()).append("\n");
        builder.append("advanceW ").append(advanceWidth()).append("\n");
        builder.append("lsb      ").append(leftSideBearing());
        
        return builder.toString();
    }
}
