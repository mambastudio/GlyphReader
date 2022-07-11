/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.read.BinaryMapReader;
import java.util.Arrays;

/**
 *
 * @author jmburu
 */
public class TrueTypeCmap0 extends CMap {
    public int format = 0;
    public int array[] = new int[256];

    public TrueTypeCmap0(BinaryMapReader file, int length) 
    {
        for (int i = 0; i < 256; i++) {
            int glyphIndex = file.getUint8();
            //System.out.format("   Glyph[%s] = %s \n", i, glyphIndex);
            this.array[i] = glyphIndex;
        }
    }
    
    @Override
    public int map(int charCode) 
    {
        if (charCode >= 0 && charCode <= 255) {
            //this.log("charCode %s maps to %s", charCode, this.array[charCode]);
            return this.array[charCode];
        }
        return 0;
    }
    
    @Override
    public String toString()
    {
        return Arrays.toString(array);
    }
}
