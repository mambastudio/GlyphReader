/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.TableRecord;
import glyphreader.read.BinaryMapReader;
import glyphreader.map.Table;
import java.util.HashMap;

/**
 *
 * @author jmburu
 */
public class HheaTable implements Table{
    public double version = 0;
    public int ascent = 0;
    public int descent = 0;
    public int lineGap = 0;
    public int advanceWidthMax = 0;
    public int minLeftSideBearing = 0;
    public int minRightSideBearing = 0;
    public int xMaxExtent = 0;
    public int caretSlopeRise = 0;
    public int caretSlopeRun = 0;
    public int caretOffset = 0;
    public int metricDataFormat = 0;
    public int numOfLongHorMetrics = 0;
    
    @Override
    public void read(BinaryMapReader file, HashMap<String, TableRecord> tables)
    {
        if(!tables.containsKey("hhea"))
            throw new UnsupportedOperationException("No hhea table");
        
        int tableOffset = tables.get("hhea").offset;
        file.seek(tableOffset);
        
        this.version = file.getFixed(); // 0x00010000
        this.ascent = file.getFword();
        this.descent = file.getFword();
        this.lineGap = file.getFword();
        this.advanceWidthMax = file.getUFword();
        this.minLeftSideBearing = file.getFword();
        this.minRightSideBearing = file.getFword();
        this.xMaxExtent = file.getFword();
        this.caretSlopeRise = file.getInt16();
        this.caretSlopeRun = file.getInt16();
        this.caretOffset = file.getFword();
        file.getInt16(); // reserved
        file.getInt16(); // reserved
        file.getInt16(); // reserved
        file.getInt16(); // reserved
        this.metricDataFormat = file.getInt16();
        this.numOfLongHorMetrics = file.getUint16();
        
        System.out.println(this);
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("version                 = ").append(version).append("\n");
        builder.append("ascent                  = ").append(ascent).append("\n");
        builder.append("descent                 = ").append(descent).append("\n");
        builder.append("lineGap                 = ").append(lineGap).append("\n");
        builder.append("advanceWidthMax         = ").append(advanceWidthMax).append("\n");
        builder.append("minLeftSideBearing      = ").append(minLeftSideBearing).append("\n");
        builder.append("minRightSideBearing     = ").append(minRightSideBearing).append("\n");
        builder.append("xMaxExtent              = ").append(xMaxExtent).append("\n");
        builder.append("caretSlopeRise          = ").append(caretSlopeRise).append("\n");
        builder.append("caretSlopeRun           = ").append(caretSlopeRun).append("\n");
        builder.append("caretOffset             = ").append(caretOffset).append("\n");
        builder.append("metricDataFormat        = ").append(metricDataFormat).append("\n");
        builder.append("numOfLongHorMetrics     = ").append(numOfLongHorMetrics).append("\n");        
        return builder.toString();
    }
}
