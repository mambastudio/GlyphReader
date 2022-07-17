/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import glyphreader.TrueTypeFont;
import glyphreader.core.FPoint2d;
import glyphreader.map.CMap;
import glyphreader.record.LongHorMetricRecord;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author user
 */
public class FXString2D {
    private final FXGlyphDraw2D glyph2D;     
    private final TrueTypeFont ttf;
    
    public FXString2D(GraphicsContext ctx, TrueTypeFont ttf, double tx, double ty)
    {
        this.glyph2D = new FXGlyphDraw2D(ctx, ttf, tx, ty);
        this.ttf = ttf;        
    }
    
    public void drawString(String text, int size) 
    {
        double sx = 0;
        double sy = 0;
        this.resetKern();

        for (int i = 0; i < text.length(); i++) {
            int index = this.mapCode(text.charAt(i));
            LongHorMetricRecord metrics = ttf.getLongHorMetricRecord(index);

            FPoint2d kern = this.nextKern(index);
            //this.log("Metrics for %s code %s index %s: %s %s kern: %s,%s", text.charAt(i),
            //    text.charAt(i), index, metrics.advanceWidth, metrics.leftSideBearing,
            //    kern.x, kern.y);
            glyph2D.drawGlyph(index, 
                    (int)(sx + kern.x) - metrics.lsb,
                    (int)(sy + kern.y), 
                    size);          
            sx += metrics.advanceWidth; 
        }      
    }
    
    public void resetKern() {        
        for (int i = 0; i < ttf.getKern0TableSize(); i++) {
            ttf.getKern0Table(i).reset();
        }
    }

    public FPoint2d nextKern(int glyphIndex) {
        FPoint2d pt;
        double x = 0, y = 0;
        for (int i = 0; i < ttf.getKern0TableSize(); i++) {
            pt = ttf.getKern0Table(i).get(glyphIndex);
            x += pt.x;
            y += pt.y;
        }
        return new FPoint2d(x, y);
    }
    
    public int mapCode(int charCode) {
        int index = 0; 
        for (int i = 0; i < ttf.getCMapSize(); i++) {
            CMap cmap = ttf.getCMap(index);
            index = cmap.map(charCode);
            if (index > 0) {
                break;
            }
            
        }
        return index;
    }
}
