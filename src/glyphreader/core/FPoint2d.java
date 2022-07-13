/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.core;

/**
 *
 * @author jmburu
 */
public class FPoint2d {
    public double x;
    public double y;
    public boolean onCurve;
    
    public FPoint2d()
    {
        
    }
    
    public FPoint2d(double x, double y)
    {
        this.x = x; this.y = y;
        onCurve = false;
    }
    
    public FPoint2d(double x, double y, boolean onCurve)
    {
        this.x = x; this.y = y;
        this.onCurve = onCurve;
    }
    
    public void set(char axis, int value)
    {
        switch (axis) {
            case 'x':
                x = value;
                break;
            case 'y':
                y = value;
                break;
            default:
                throw new UnsupportedOperationException("unrecognised axis");
        }
    }
    
    @Override
    public String toString()
    {
        return String.format("(%3.2f, %3.2f, %b)", x, y, onCurve);
    }
}
