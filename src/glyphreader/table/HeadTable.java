/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.record.TableRecord;
import glyphreader.read.BinaryMapReader;
import glyphreader.FUtility;
import glyphreader.core.FBound;
import glyphreader.map.AbstractTable;
import static glyphreader.map.Table.TableType.HEAD;
import glyphreader.map.TableList;
import glyphreader.read.BinaryReader;
import java.util.Date;

/**
 *
 * @author jmburu
 */
public class HeadTable extends AbstractTable{
    public int  version             = 0;
    public double  fontRevision     = 0;
    public int  checksumAdjustment  = 0;
    public int  magicNumber         = 0;    
    public int  flags               = 0;
    public int  unitsPerEm          = 0;
    public Date created             = null;
    public Date modified            = null;
    public int  xMin                = 0;
    public int  yMin                = 0;
    public int  xMax                = 0;
    public int  yMax                = 0;
    public int  macStyle            = 0;
    public int  lowestRecPPEM       = 0;
    public int  fontDirectionHint   = 0;
    public int  indexToLocFormat    = 0;
    public int  glyphDataFormat     = 0;
    
   
    public HeadTable(TableRecord record)
    {
        super(record);        
    }
    
    @Override
    public boolean read(BinaryReader file, TableList tables)
    {       
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        this.version =  (int) file.getFixed(); 
        this.fontRevision = file.getFixed();  
        this.checksumAdjustment = file.getUint32();
        this.magicNumber = file.getUint32(); 
        FUtility.assertCheck(this.magicNumber == 0x5f0f3cf5);
        this.flags = file.getUint16();
        this.unitsPerEm = file.getUint16();
        this.created = file.getDate();
        this.modified = file.getDate();
        this.xMin = file.getFword();
        this.yMin = file.getFword();
        this.xMax = file.getFword();
        this.yMax = file.getFword();
        this.macStyle = file.getUint16();
        this.lowestRecPPEM = file.getUint16();
        this.fontDirectionHint = file.getInt16();
        this.indexToLocFormat = file.getInt16();
        this.glyphDataFormat = file.getInt16();
        
        return true;
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("version             = ").append(version).append("\n");
        builder.append("fontRevision        = ").append(fontRevision).append("\n");
        builder.append("checksumAdjustment  = ").append(checksumAdjustment).append("\n");
        builder.append("magicNumber         = ").append(magicNumber).append("\n");
        builder.append("flags               = ").append(flags).append("\n");
        builder.append("unitsPerEm          = ").append(unitsPerEm).append("\n");
        builder.append("created             = ").append(created).append("\n");
        builder.append("modified            = ").append(modified).append("\n");
        builder.append("xMin                = ").append(xMin).append("\n");
        builder.append("yMin                = ").append(yMin).append("\n");
        builder.append("xMax                = ").append(xMax).append("\n");
        builder.append("yMax                = ").append(yMax).append("\n");
        builder.append("macStyle            = ").append(macStyle).append("\n");
        builder.append("lowestRecPPEM       = ").append(lowestRecPPEM).append("\n");
        builder.append("fontDirectionHint   = ").append(fontDirectionHint).append("\n");
        builder.append("indexToLocFormat    = ").append(indexToLocFormat).append("\n");
        builder.append("glyphDataFormat     = ").append(glyphDataFormat);        
        return builder.toString();
    }
    
    public FBound getBound()
    {
        return new FBound(xMin, yMin, xMax, yMax);
    }

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return HEAD;
    }
}
