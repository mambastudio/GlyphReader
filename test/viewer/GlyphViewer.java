/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import glyphreader.core.FBound;
import glyphreader.Glyph;
import glyphreader.GlyphContent;
import glyphreader.core.FPoint2d;
import glyphreader.TrueTypeFont;
import glyphreader.core.FPoint2i;
import glyphreader.map.CMap;
import java.nio.file.Paths;
import java.util.function.Consumer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class GlyphViewer extends Application{
    
    private GlyphContent glyphList;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Pane baseDrawPanel = new Pane();
        BackgroundPane backgroundPanel = new BackgroundPane();
        
        //three quarter size of the screen monitor
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();        
        Scene scene = new Scene(baseDrawPanel, screenBounds.getWidth() * 0.95, screenBounds.getHeight() * 0.85);
        
       // TODO    
        ResizeableCanvas renderCanvas = new ResizeableCanvas(500, 500);  
        
        //draw call
        initDraw(renderCanvas);
        
        baseDrawPanel.getChildren().addAll(backgroundPanel, renderCanvas);
        
        //ensure they grow according to base draw panel
        backgroundPanel.prefWidthProperty().bind(baseDrawPanel.widthProperty());
        backgroundPanel.prefHeightProperty().bind(baseDrawPanel.heightProperty());        
        renderCanvas.prefWidthProperty().bind(baseDrawPanel.widthProperty());
        renderCanvas.prefHeightProperty().bind(baseDrawPanel.heightProperty());
        
        primaryStage.setScene(scene);
       // primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public boolean drawGlyph(Glyph glyph, GraphicsContext ctx, int x, int y)
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
    
    public void initDraw(ResizeableCanvas renderCanvas)
    {
        TrueTypeFont ttf = new TrueTypeFont(Paths.get("C:\\Users\\jmburu\\Downloads\\Noto_Serif", "NotoSerif-Regular.ttf"));
        glyphList = new GlyphContent(ttf);
        
        renderCanvas.setDrawGlyph(this.drawText("First try of javafx custom glyphs", glyphList, 70, 0, 125));        
        //renderCanvas.setDrawGlyph(this.drawAllGlyphs(renderCanvas, glyphList));
        
    }
    
    public Consumer<GraphicsContext> drawText(String text, GlyphContent glyphList, final double size, final double x, final double y)
    {
        return (ctx)->{
            
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
                ctx.save();
                ctx.translate(x, y);
                this.scale(ctx, size);
                ctx.beginPath();
                if(this.drawGlyph(glyphList.get(index), ctx, (int)(sx + kern.x),//- metrics.leftSideBearing, 
                    (int)(sy + kern.y)))
                   ctx.fill();
                ctx.restore();
                
                sx += metrics.x; //metrics.advanceWidth;
            }
            
            
            
        };
    }
    
    public Consumer<GraphicsContext> drawAllGlyphs(
            ResizeableCanvas renderCanvas, 
            GlyphContent glyphList)
    {
        return (ctx)->{
            FBound fbounds = glyphList.getGlyphBound();
            double fwidth = fbounds.getWidth();
            double fheight = fbounds.getHeight(); 
            
            double fscale = 64 / glyphList.getUnitsPerEm();
                        
            double pixID = -fwidth; //added in the for loop to become 0
            
            for(int i = 0; i<glyphList.size(); i++)
            {
                pixID += fwidth; 
                int x = (int) (pixID % (renderCanvas.getWidth()/fscale));
                int y = (int) (pixID / (renderCanvas.getWidth()/fscale)) ;
                
                double pixIDN = pixID + fwidth;                
                int yN = (int) (pixIDN / (renderCanvas.getWidth()/fscale));
                
                if(yN != y)
                {
                    pixID += fwidth;
                    x = (int) (pixID % (renderCanvas.getWidth()/fscale));
                    y = (int) (pixID / (renderCanvas.getWidth()/fscale)) ;
                }
                                    
                ctx.save();
                ctx.scale(fscale, -fscale);
                ctx.translate(x + fbounds.xMin, fbounds.yMin - fheight - (y * fheight));                
                ctx.setFill(Color.BLACK);
                ctx.beginPath();
                
                if (drawGlyph(glyphList.get(i), ctx, 0, 0)) {
                    ctx.fill();
                    
                }
                ctx.restore();
            }
        };
    }
    
    private void scale(GraphicsContext ctx, double size)
    {
        ctx.scale(size / this.glyphList.getUnitsPerEm(), -size / this.glyphList.getUnitsPerEm());
        
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
    
    public void drawSingleGlyph(GraphicsContext ctx, int glyphIndex,
        int x, int y, int size) 
    {
        ctx.save();
        ctx.translate(x, y);
        this.scale(ctx, size);
        this.drawGlyph(glyphList.get(glyphIndex), ctx, 0, 0);
        ctx.restore();
    }
}
