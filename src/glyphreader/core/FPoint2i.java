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
public class FPoint2i {
    public int x;
    public int y;
   
    
    public FPoint2i()
    {
        
    }
    
    public FPoint2i(int x, int y)
    {
        this.x = x; this.y = y;        
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
        return String.format("(%3d, %3d)", x, y);
    }
}
