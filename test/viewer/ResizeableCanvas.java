/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import java.util.function.Consumer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
   
/**
 *
 * @author user
 */
public final class ResizeableCanvas extends Region 
{
    private final Canvas canvas;  
    Consumer<GraphicsContext> consume;
                    
    public ResizeableCanvas(double width, double height) 
    {        
        //set the width and height of this and the canvas as the same
        setWidth(width);
        setHeight(height);
        canvas = new Canvas(width, height);
        //add the canvas as a child
        getChildren().add(canvas);
        //bind the canvas width and height to the region
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
        canvas.widthProperty().addListener((o, oV, nv)->{
            draw();
        });
        canvas.heightProperty().addListener((o, oV, nv)->{
            draw();
        });        
    }
  
    
    public GraphicsContext getGraphicsContext2D() {
        return canvas.getGraphicsContext2D();
    }

    
    public void draw() {
        getGraphicsContext2D().clearRect(0, 0, Float.MAX_VALUE, Float.MAX_VALUE);   
        
               
        drawGlyph(consume);
        
    }
      
    private void drawGlyph(Consumer<GraphicsContext> consume)
    {
        if(consume != null)
            consume.accept(getGraphicsContext2D());
    }
    
    public void setDrawGlyph(Consumer<GraphicsContext> consume)
    {
        this.consume = consume;
    }
}

