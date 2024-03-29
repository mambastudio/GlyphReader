/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.glyf.Glyph;
import glyphreader.core.metrics.FGlyphMetrics;
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
        
        TrueTypeFont font = new TrueTypeFont(Paths.get("C:\\Users\\jmburu\\Downloads\\Noto_Serif", "NotoSerif-Regular.ttf"));
        Glyph glyph = font.getGlyph(21);
        FGlyphMetrics hmetric = glyph.getGlyphMetrics();
        
        System.out.println(hmetric);
    }
    
}
