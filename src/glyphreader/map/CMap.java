/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

/**
 *
 * @author jmburu
 */
public abstract class CMap {
    public int number;
    
    public abstract int map(int charcode);
    
    
    protected class Segment{
        public int idRangeOffset;
        public int startCode;
        public int endCode;
        public int idDelta;
    }
}
