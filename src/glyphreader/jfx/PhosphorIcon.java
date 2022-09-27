/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.jfx;

import glyphreader.FontType;
import glyphreader.fonts.notoserif.Resource;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class PhosphorIcon extends GlyphNode{
    
    private static final FontType font = FontType.font(Resource.class, "Phosphor.ttf", 20);
    
    private final StringProperty glyphName;
    private final DoubleProperty glyphSize;
    public PhosphorIcon()
    {
        super(font, ".notdef");
        this.glyphName = new SimpleStringProperty(".notdef");
        this.glyphName.addListener((o, ov, nv)->{
            if(nv == null)
                super.glyph = font.getGlyph(0);
            else
                super.glyph = font.getGlyph(nv);
            applyGlyphCentered();
        });
        this.glyphSize = new SimpleDoubleProperty(20);
        this.glyphSize.addListener((o, ov, nv)->{
            if(nv == null || nv.doubleValue() < 5d)
                super.size = 5;
            else
                super.size = nv.doubleValue();
            applyGlyphCentered();
        });
        
    }
    
    public String getGlyphName()
    {
        return glyphName.get();
    }
    
    public void setGlyphName(String glyphName)
    {
        this.glyphName.set(glyphName);
    }
    
    public StringProperty glyphNameProperty()
    {
        return glyphName;
    }
    
    public double getGlyphSize()
    {
        return glyphSize.get();
    }
    
    public void setGlyphSize(double glyphSize)
    {
        this.glyphSize.set(glyphSize);
    }
    
    public DoubleProperty glyphSizeProperty()
    {
        return glyphSize;
    }
}
