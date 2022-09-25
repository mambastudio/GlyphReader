/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import glyphreader.glyf.Glyph;
import glyphreader.TrueTypeFont;
import glyphreader.core.FPoint2d;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author user
 */
public class FXGlyphDraw2D {
    private final GraphicsContext ctx;
    private final double unitsPerEm;
    private final TrueTypeFont ttf;
    private final double tx, ty;
    
    public FXGlyphDraw2D(GraphicsContext ctx, TrueTypeFont ttf, double tx, double ty)
    {
        this.ctx = ctx;
        this.unitsPerEm = ttf.getUnitsPerEm();
        this.ttf = ttf;
        this.tx = tx; 
        this.ty = ty;
    }
    
    private void scale(GraphicsContext ctx, double size)
    {
        ctx.scale(size / this.unitsPerEm, -size / unitsPerEm);
        
    }
    
    private boolean draw(Glyph glyph, int x, int y)
    {
        if ( glyph == null) {
            return false;
        }
        
        int s = 0,
            p = 0,
            c = 0,
            contourStart = 0;
        FPoint2d  prev;
        
        for (; p < glyph.points.size(); p++) {
            FPoint2d point = glyph.points.get(p);
            switch (s) {
                case 0:
                    ctx.moveTo(point.x + x, point.y + y);
                    s = 1;
                    break;
                case 1:
                    if (point.onCurve) {
                        ctx.lineTo(point.x + x, point.y + y);
                    } else {
                        s = 2;
                    }   break;
                default:
                    prev = glyph.points.get(p - 1);
                    if (point.onCurve) {
                        ctx.quadraticCurveTo(prev.x + x, prev.y + y,
                                point.x + x, point.y + y);
                        s = 1;
                    } else {
                        ctx.quadraticCurveTo(prev.x + x, prev.y + y,
                                (prev.x + point.x) / 2 + x,
                                (prev.y + point.y) / 2 + y);
                    }   break;
            }

            if (p == glyph.contourEnds.get(c)) {
                if (s == 2) { // final point was off-curve. connect to start
                    prev = point;
                    point = glyph.points.get(contourStart);
                    if (point.onCurve) {
                        ctx.quadraticCurveTo(prev.x + x, prev.y + y,
                            point.x + x, point.y + y);
                    } else {
                        ctx.quadraticCurveTo(prev.x + x, prev.y + y,
                            (prev.x + point.x) / 2 + x,
                            (prev.y + point.y) / 2 + y);
                    }
                }
                contourStart = p + 1;
                c += 1;
                s = 0;
            }
        }

        return true;
    }
    
    public void drawGlyph(int glyphIndex,
        int x, int y, int size) 
    {
        ctx.save();
        ctx.translate(tx, ty);
        this.scale(ctx, size);
        ctx.beginPath();
        if(draw(ttf.getGlyph(glyphIndex), x, y))
            ctx.fill();        
        ctx.restore();
    }
}
