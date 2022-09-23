/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.core.FBound;
import glyphreader.core.metrics.FHorizontalMetrics;
import java.nio.file.Path;

/**
 *
 * @author jmburu
 */
public class FontType 
{
    
    private FHorizontalMetrics hmetrics;
    
    private String name;
    private double size;    
    private String family;
    
    private FBound bound;
    
    protected TrueTypeFontInfo fontTTF = null;
    
    public FontType(String name)
    {
        this.name = name;
    }
    
    public FontType(String name, double size)
    {
        this(name);
        this.size = size;
    }
    
    protected FontType(TrueTypeFontInfo info, double size)
    {
        
    }
    
    public Path getPath()
    {
        return null;
    }
    
    public boolean isOpenType()
    {
        return true;
    }
}
