/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.core.FBound;
import glyphreader.core.FPoint;
import java.util.ArrayList;

/**
 *
 * @author jmburu
 */
public class Glyph {
    public ArrayList<Integer> contourEnds;
    public int numberOfContours;
    public ArrayList<FPoint> points;
    public FBound bound;
        
    public Glyph()
    {
        contourEnds = new ArrayList();
        numberOfContours = 0;
        points = new ArrayList();
        bound = new FBound();
    }
}
