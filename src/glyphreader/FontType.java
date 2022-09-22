/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.core.FBound;
import glyphreader.core.metrics.FHorizontalMetrics;
import java.io.InputStream;

/**
 *
 * @author jmburu
 */
public class FontType {
    FHorizontalMetrics hmetrics;
    String name;
    double size;
    
    String family;
    
    FBound bound;
    
    private TrueTypeFont fontTTF;
    
    public FontType(String name)
    {
        this.name = name;
    }
    
    public FontType(String name, double size)
    {
        this(name);
        this.size = size;
    }
    
    public FontType loadFont(InputStream in, double size)
    {
        return null;
    }
}
