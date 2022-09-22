/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.core.FBound;
import glyphreader.core.FPoint2d;
import glyphreader.core.metrics.FGlyphMetrics;
import glyphreader.record.LongHorMetricRecord;
import java.util.ArrayList;

/**
 *
 * @author jmburu
 */
public class Glyph {
    public ArrayList<Integer> contourEnds;
    public int numberOfContours;
    public ArrayList<FPoint2d> points;
    public FBound glyphBound;
    public FBound fontBound;
    
    //glyph horizontal metrics (glyph specific)
    private LongHorMetricRecord lmetrics = null;
   
        
    public Glyph()
    {
        contourEnds = new ArrayList();
        numberOfContours = 0;
        points = new ArrayList();
        glyphBound = new FBound();
    }
    
    public void setLongHorMetricRecord(LongHorMetricRecord record)
    {
        this.lmetrics = record;
    }
    
    public LongHorMetricRecord getLongHorMetricRecord()
    {
        return this.lmetrics;
    }
    
    public FGlyphMetrics getGlyphMetrics()
    {
        return new FGlyphMetrics(this);
    }
}
