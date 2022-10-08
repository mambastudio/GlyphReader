/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import glyphreader.jfx.GlyphNode;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 *
 * @author jmburu
 */
public class PanDisplay extends ScrollPane{
    
    GlyphNode target;
    StackPane content;
    Pane overlay;
    
    double size;
    Point2D targetMousePoint = Point2D.ZERO;
    
    public PanDisplay(GlyphNode glyphNode)
    {
        super();
        this.target = glyphNode;      
        this.content = new StackPane(glyphNode, overlay = new Pane());
        this.size = target.getSize();
        
        this.setContent(content);
        
        content.setOnScroll(e ->{
            zoom(e.getTextDeltaY(), new Point2D(e.getX(), e.getY()));           
        });
        overlay.setOnScroll(e->{               
            System.out.println(e.getX());
            System.out.println(this.widthProperty().get());
            targetMousePoint = new Point2D(e.getX(), e.getY());
        });
        
        this.setPannable(true);
        
        this.viewportBoundsProperty().addListener((observable, oldBounds, newBounds) -> {            
            content.setMinWidth(newBounds.getWidth() - 20);
            content.setMinHeight(newBounds.getHeight() - 20);            
        });
        overlay.maxWidthProperty().bind(this.widthProperty());
        overlay.maxHeightProperty().bind(this.heightProperty());
    }
        
    public void zoom(double wheelDelta, Point2D mousePointContent)
    {        
        size = wheelDelta > 1 ? size + 20 : size - 20;        
        
        
        Bounds vBounds = this.viewportBoundsProperty().get();
        
        double xM = mousePointContent.getX()/content.getLayoutBounds().getWidth();
        double yM = mousePointContent.getY()/content.getLayoutBounds().getHeight();
        
        System.out.println(new Point2D(xM, yM));
        System.out.println(targetMousePoint);
        
                
        target.adjustScale(size);
        
        this.setHvalue(0.5);
        this.setVvalue(0.5);
        
        //System.out.println(mousePoint);
    }  
    
   
}
