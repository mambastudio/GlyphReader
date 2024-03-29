/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.read;

import glyphreader.FUtility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
public class BinaryMapReader implements BinaryReader{
    private FileChannel channel;
    private RandomAccessFile rfile;
    
    protected MappedByteBuffer buffer; 
    
    WeakReference<MappedByteBuffer> bufferWeakRef;
    
    public BinaryMapReader(String directoryPath, String fileName) throws IOException
    {
        Path path = FileSystems.getDefault().getPath(directoryPath, fileName);
        try {
            rfile = new RandomAccessFile(path.toFile(), "rw");  
            channel = rfile.getChannel();
            buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, rfile.length());
            bufferWeakRef = new WeakReference<>(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BinaryMapReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BinaryMapReader.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
    
    public BinaryMapReader(URI uri)
    {
        File file = new File(uri);
        Path path = file.toPath();
        
        try {
            rfile = new RandomAccessFile(path.toFile(), "rw");  
            channel = rfile.getChannel();
            buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            bufferWeakRef = new WeakReference<>(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BinaryMapReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BinaryMapReader.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
    
    public BinaryMapReader(Path path)
    {        
        try {
            rfile = new RandomAccessFile(path.toFile(), "rw");  
            channel = rfile.getChannel();
            buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, Files.size(path));
            bufferWeakRef = new WeakReference<>(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BinaryMapReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BinaryMapReader.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public BinaryMapReader(File file)
    {
        this(file.toURI());
    }
    
    @Override
    public void rewind(){
        buffer.rewind();
    }
    
    @Override
    public long length()
    {
        try {
            return rfile.length();
        } catch (IOException ex) {
            Logger.getLogger(BinaryMapReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    

    //https://stackoverflow.com/questions/2972986/how-to-unmap-a-file-from-memory-mapped-using-filechannel-in-java
    private void unmap()
    {
        buffer = null;
        final long startTime = System.currentTimeMillis();
        while(null != bufferWeakRef.get()) {
            if(System.currentTimeMillis() - startTime > 10)
                // give up
                return;
            System.gc();
            Thread.yield();
        }
    }
    
    @Override
    public void close()
    {
        try {
          
            
            channel.force(false);  // doesn't help
            channel.close();       // doesn't help            
            rfile.close();           // try to make sure that this thing is closed!!!!!
            unmap();
            
        } catch (IOException ex) {
            Logger.getLogger(BinaryMapReader.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private String fromCharCode(Charset charset, byte... codePoints)
    {
        return new String(codePoints, charset);
    }

    @Override
    public String getString(int length, Charset charset)
    {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i<length; i++)
            result.append(fromCharCode(charset, (byte)this.getUint8()));
        return result.toString();
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
    
