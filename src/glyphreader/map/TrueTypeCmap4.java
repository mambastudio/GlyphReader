/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.read.BinaryMapReader;
import java.util.HashMap;

/**
 *
 * @author jmburu
 */
public class TrueTypeCmap4 extends CMap{
    public int format = 4;    
    
    private final HashMap<Integer, Integer> cache = new HashMap<>();    
    private final Segment segments[];
    private final BinaryMapReader file;
    
    public TrueTypeCmap4(BinaryMapReader file, int length)
    {
        this.file = file; 
        
        // 2x segcount
        int segCount = file.getUint16() / 2;
        
        segments = new Segment[segCount];
        
        // 2 * (2**floor(log2(segCount)))
        int searchRange = file.getUint16();
        // log2(searchRange)
        int entrySelector = file.getUint16();
        // (2*segCount) - searchRange
        int rangeShift = file.getUint16();
        
        // Ending character code for each segment, last is 0xffff
        for (int i = 0; i < segCount; i++) {
            Segment segment = new Segment();
            segment.idRangeOffset = 0;
            segment.startCode = 0;
            segment.endCode = file.getUint16();
            segment.idDelta = 0;
            segments[i] = segment;            
        }
        
        // reservePAd
        file.getUint16();

        // starting character code for each segment
        for (int i = 0; i < segCount; i++) {
            segments[i].startCode = file.getUint16();
        }

        // Delta for all character codes in segment
        for (int i = 0; i < segCount; i++) {
            segments[i].idDelta = file.getUint16();
        }

        // offset in bytes to glyph indexArray, or 0
        for (int i = 0; i < segCount; i++) {
            int ro = file.getUint16();
            if (ro > 0) {
                segments[i].idRangeOffset = file.tell() - 2 + ro;
            } else {
                segments[i].idRangeOffset = 0;
            }
        }
        
        
        for(int i = 0; i < segCount; i++) {
            Segment seg = segments[i];
            //System.out.format("   segment[%s] = %s %s %s %s \n", i,
            //    seg.startCode, seg.endCode, seg.idDelta, seg.idRangeOffset);
        }
        
    }
    
    @Override
    public int map(int charCode) {
        
        if (!(cache.containsKey(charCode))) {      
            int j;
            for (j = 0; j < this.segments.length; j++) {
                Segment segment = this.segments[j];
                if (segment.startCode <= charCode && segment.endCode >=
                    charCode) {
                    int index, glyphIndexAddress = 0;
                    if (segment.idRangeOffset>0) {
                        glyphIndexAddress = segment.idRangeOffset + 2 *
                            (charCode - segment.startCode);
                        this.file.seek(glyphIndexAddress);
                        index = this.file.getUint16();
                    } else {
                        index = (segment.idDelta + charCode) & 0xffff;
                    }

                    System.out.format("Charcode %s is between %s and %s; maps to %s (%s) roffset=%s",
                        charCode, segment.startCode, segment.endCode,
                        glyphIndexAddress, index, segment.idRangeOffset);

                    this.cache.put(charCode, index);
                    break;
                }
            }

            if (j == this.segments.length) {
                this.cache.put(charCode, 0);
            }
        }

        return this.cache.get(charCode);
    }
}
