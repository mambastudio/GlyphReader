/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.read;

import glyphreader.FUtility;
import glyphreader.codec.Decode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmburu
 */
public class BinaryBufferReader implements BinaryReader{
    ByteBuffer buffer;
    InputStream inputStream;
    
    public BinaryBufferReader(Class<?> clazz, String file)
    {
        try {
            inputStream = clazz.getResourceAsStream(file);
            buffer = ByteBuffer.allocate(inputStream.available());
            Channels.newChannel(inputStream).read(buffer);
        } catch (IOException ex) {
            Logger.getLogger(BinaryBufferReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BinaryBufferReader(InputStream inputStream)
    {
        try {
            this.inputStream = inputStream;
            buffer = ByteBuffer.allocate(inputStream.available());
            Channels.newChannel(inputStream).read(buffer);
        } catch (IOException ex) {
            Logger.getLogger(BinaryBufferReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void rewind(){
        buffer.rewind();
    }
    
    @Override
    public long length()
    {
        return buffer.array().length;        
    }
        
    @Override
    public void close()
    {
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(BinaryBufferReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int seek(int pos) {
        FUtility.assertCheck(pos >= 0 && pos <= this.length());
        int oldPos = this.buffer.position();
        this.buffer.position(pos);
        return oldPos;
    }
    
    @Override
    public int tell() {
        return this.buffer.position();
    }
    
    @Override
    public int getInt8()
    {
       int signedByte = buffer.get();
       return signedByte;
    }
    
    @Override
    public int getUint8()
    {
        int unsignedByte = Byte.toUnsignedInt(buffer.get());        
        return unsignedByte;
    }
    
        
    @Override
    public int getUint16() {
        return Short.toUnsignedInt(buffer.getShort());
    }
    
    @Override
    public int getInt16() {
        return buffer.getShort();
    }
    
    @Override
    public int getInt32()
    {
        return buffer.getInt();
    }
    
    @Override
    public int getUint32() {
        return (int)(Integer.toUnsignedLong(buffer.getInt()));
    }

    @Override
    public long getInt64() {
        return buffer.getLong();
    }
                
    @Override
    public int getFword() {
        return this.getInt16();
    }

    @Override
    public int getUFword() {
        return this.getUint16();
    }

    @Override
    public double get2Dot14() {
        return this.getInt16() / Math.pow(2, 14);
    }

    @Override
    public double getFixed() {        
        return getInt32()/ Math.pow(2, 16);
    }
    
    @Override
    public double getVersion16Dot16()
    {
        return getFixed();
    }
    
    @Override
    public String getString(int length, Charset charset)
    {   
        return Decode.getString(this, length, charset);
    }
    
    @Override
    public Date getDate() {
        long macTime = getInt64();
        
        //date of 1904-jan-1
        final GregorianCalendar startDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        startDate.clear();
        startDate.set(1904, Calendar.JANUARY, 1);
        
        long utcTime = macTime * 1000 + startDate.getTimeInMillis();        
        
        return new Date(utcTime);
    }
}
