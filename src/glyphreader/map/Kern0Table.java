/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.read.BinaryMapReader;
import glyphreader.core.FPoint2d;
import glyphreader.read.BinaryReader;
import java.util.HashMap;

/**
 *
 * @author jmburu
 */
public class Kern0Table {
    boolean swap;
    int offset;
    int nPairs;
    public HashMap<Integer, Integer> map; 
    int oldIndex = -1;
        
    public Kern0Table(BinaryReader file, boolean vertical, boolean cross)
    {
        this.map = new HashMap();
        this.swap = vertical && !cross || !vertical && cross;
        //this.file = file;
        this.offset = file.tell();
        this.nPairs = file.getUint16();
        file.getUint16(); // searchRange
        file.getUint16(); // entrySelector
        file.getUint16(); // rangeShift

        for (int i = 0; i < this.nPairs; i++) {
            int left = file.getUint16();
            int right = file.getUint16();
            int value = file.getFword();
            this.map.put((left << 16) | right, value);
            //System.out.format("Kern %s/%s->%s", left, right, value);
        }
        this.reset();
    }
    
    public final void reset() {
        this.oldIndex = -1;
    }

    public FPoint2d get(int glyphIndex) {
        int x = 0;
        if (this.oldIndex >= 0) {
            int ch = (this.oldIndex << 16) | glyphIndex;
            if (map.containsKey(ch)) {
                x = this.map.get(ch);
            }
            System.out.format("Lookup kern pair %s/%s -> %s (%s)",
                this.oldIndex, glyphIndex, x, ch);
        }
        this.oldIndex = glyphIndex;
        if (this.swap) {
            FPoint2d pt = new FPoint2d();
            pt.x = 0;
            pt.y = x;
            return pt;
        } else {
            FPoint2d pt = new FPoint2d();
            pt.x = x;
            pt.y = 0;
            return pt;
        }
    }
}
