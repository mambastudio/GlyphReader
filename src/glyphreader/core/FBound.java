/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.core;

/**
 *
 * @author user
 */
public class FBound {
    public double xMin, yMin;
    public double xMax, yMax;
   
    public FBound()
    {
        this(0, 0, 0, 0);
    }
    
    public FBound(double xMin, double yMin, double xMax, double yMax)
    {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }
    
    public double getWidth()
    {
        return xMax - xMin;
    }
    
    public double getHeight()
    {
        return yMax - yMin;
    }
}
