/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.core.FBound;
import glyphreader.core.FPoint2d;
import glyphreader.core.metrics.FGlyphMetrics;
import glyphreader.core.metrics.FHorizontalMetrics;
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
    public FBound bound;
    
    private LongHorMetricRecord lmetrics = null;
    private FHorizontalMetrics hmetrics = null;
        
    public Glyph()
    {
        contourEnds = new ArrayList();
        numberOfContours = 0;
        points = new ArrayList();
        bound = new FBound();
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
    
    public void setFHorizontalMetrics(FHorizontalMetrics hmetrics)
    {
        this.hmetrics = hmetrics;
    }
    
    public FHorizontalMetrics getFHorizontalMetrics()
    {
        return hmetrics;
    }
}
