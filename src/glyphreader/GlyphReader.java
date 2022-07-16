/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.core.FPoint2d;
import java.nio.file.Paths;

/**
 *
 * @author jmburu
 */
public class GlyphReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        TrueTypeFont font = new TrueTypeFont(Paths.get("C:\\Users\\user\\Downloads\\PT_Serif", "PTSerif-Regular.ttf"));
        Glyph glyph = font.readGlyph(4);
        
    }
    
}
