/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.core.metrics;

import glyphreader.TrueTypeFont;
import glyphreader.core.FBound;

/**
 *
 * @author user
 */
public class FGlobalMetrics {
    FBound bound;
    
    public FGlobalMetrics(TrueTypeFont ttf)
    {
        
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
    
    public double width()
    {
        return xMax() - xMin();
    }
    
    public double height()
    {
        return yMax() - yMin();
    }
}
