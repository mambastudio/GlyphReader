/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.read;

import java.util.Date;

/**
 *
 * @author user
 */
public interface BinaryReader {
    public void rewind();
    public long length();
    public void close();
    public int seek(int pos);
    public int tell();
    
    //reading file
    public int getInt8();
    public int getUint8();
    public int getUint16();
    public int getInt16();
    public int getInt32();
    public int getUint32();
    public long getInt64();
    public int getFword();
    public int getUFword();
    public double get2Dot14();
    public double getFixed();
    public double getVersion16Dot16();
    public String getString(int length);
    public String getUnicodeString(int length);
    public Date getDate();
}
