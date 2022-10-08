/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import glyphreader.FontType;
import glyphreader.fonts.notoserif.Resource;
import glyphreader.jfx.GlyphNode;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author jmburu
 */
public class SingleGlyphNodeViewPannable extends Application{
    
    double size = 64;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane baseDrawPanel = new StackPane();
        
        //three quarter size of the screen monitor
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();        
        Scene scene = new Scene(baseDrawPanel, screenBounds.getWidth() * 0.95, screenBounds.getHeight() * 0.85);
        
        FontType font = FontType.font(Resource.class, "FontAwesome.ttf", size);
        GlyphNode node = new GlyphNode(font, "W");
      
        baseDrawPanel.getChildren().add(new PanDisplay(node));
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Single Glyph Node View Pannable");
       // primaryStage.setMaximized(true);
        primaryStage.show();
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
