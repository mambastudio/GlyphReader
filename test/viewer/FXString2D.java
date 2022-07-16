/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import glyphreader.GlyphContent;
import glyphreader.core.FPoint2d;
import glyphreader.core.FPoint2i;
import glyphreader.map.CMap;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author user
 */
public class FXString2D {
    private final FXGlyphDraw2D glyph2D;     
    private final GlyphContent glyphList;
    
    public FXString2D(GraphicsContext ctx, GlyphContent glyphList, double tx, double ty)
    {
        this.glyph2D = new FXGlyphDraw2D(ctx, glyphList, tx, ty);
        this.glyphList = glyphList;        
    }
    
    public void drawString(String text, int size) 
    {
        double sx = 0;
        double sy = 0;
        this.resetKern();

        for (int i = 0; i < text.length(); i++) {
            int index = this.mapCode(text.charAt(i));
            FPoint2i metrics = glyphList.getHorizontalMetrics(index);

            FPoint2d kern = this.nextKern(index);
            //this.log("Metrics for %s code %s index %s: %s %s kern: %s,%s", text.charAt(i),
            //    text.charAt(i), index, metrics.advanceWidth, metrics.leftSideBearing,
            //    kern.x, kern.y);
            glyph2D.drawGlyph(index, 
                    (int)(sx + kern.x), //- metrics.leftSideBearing,
                    (int)(sy + kern.y), 
                    size);          
            sx += metrics.x; //metrics.advanceWidth;
        }      
    }
    
    public void resetKern() {        
        for (int i = 0; i < glyphList.getKernSize(); i++) {
            glyphList.getKern0Table(i).reset();
        }
    }

    public FPoint2d nextKern(int glyphIndex) {
        FPoint2d pt;
        double x = 0, y = 0;
        for (int i = 0; i < glyphList.getKernSize(); i++) {
            pt = glyphList.getKern0Table(i).get(glyphIndex);
            x += pt.x;
            y += pt.y;
        }
        return new FPoint2d(x, y);
    }
    
    public int mapCode(int charCode) {
        int index = 0; 
        for (int i = 0; i < glyphList.getCMapSize(); i++) {
            CMap cmap = glyphList.getCMap(index);
            index = cmap.map(charCode);
            if (index > 0) {
                break;
            }
            
        }
        return index;
    }
}
