/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.codec;

import glyphreader.read.BinaryReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        byte[] b = new byte[length];
        
        for(int i = 0; i<codePoints.length; i++)
        {
            int code = reader.getUint8();
            b[i] = (byte)code;
            codePoints[i] = (byte) code;
        }  
        try {
            return new String(b, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String decodeUTC16(BinaryReader reader, int length)
    {
        int[] codePoints = new int[length];
        byte[] b = new byte[length];
        
        for(int i = 0; i<codePoints.length; i++)
        {
            int code = reader.getUint8();
            b[i] = (byte)code;
            codePoints[i] = (byte) code;
        }  
        try {
            return new String(b, "UTF-16");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String decodeUTC16BE(BinaryReader reader, int length)
    {
        int[] codePoints = new int[length];
        byte[] b = new byte[length];
        
        for(int i = 0; i<codePoints.length; i++)
        {
            int code = reader.getUint8();
            b[i] = (byte)code;
            codePoints[i] = (byte) code;
        }  
        try {
            return new String(b, "UTF-16BE");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String decodeUTC16LE(BinaryReader reader, int length)
    {
        int[] codePoints = new int[length];
        byte[] b = new byte[length];
        
        for(int i = 0; i<codePoints.length; i++)
        {
            int code = reader.getUint8();
            b[i] = (byte)code;
            codePoints[i] = (byte) code;
        }  
        try {
            return new String(b, "UTF-16LE");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
