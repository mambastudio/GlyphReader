/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.codec;

import glyphreader.read.BinaryReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author jmburu
 */
public class Decode {
    public static String getString(BinaryReader reader, int length, Charset charset)
    {
        if(charset == StandardCharsets.UTF_8)
            return decodeUTC8(reader, length);
        else if(charset == StandardCharsets.UTF_16)
            return decodeUTC16(reader, length);
        return null;
    }
    
    public static String decodeUTC8(BinaryReader reader, int length)
    {
        int[] codePoints = new int[length];
        for(int i = 0; i<codePoints.length; i++)
        {
            int code = reader.getUint8();
            codePoints[i] = (byte) code;
        }        
        return new String(codePoints, 0, codePoints.length);
    }
    
    public static String decodeUTC16(BinaryReader reader, int length)
    {
        int[] codePoints = new int[length/2];
        for(int i = 0; i<codePoints.length; i++)
        {
            int code = reader.getUint16();
            codePoints[i] = code;
        }        
        return new String(codePoints, 0, codePoints.length);
    }
}
