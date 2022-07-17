/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.AbstractTable;
import glyphreader.record.LongHorMetricRecord;
import static glyphreader.map.Table.TableType.HMTX;
import glyphreader.map.TableList;
import glyphreader.record.TableRecord;
import glyphreader.read.BinaryMapReader;
import java.util.ArrayList;

/**
 *
 * @author jmburu
 */
public class HmtxTable extends AbstractTable{
    public ArrayList<LongHorMetricRecord> hmtxData = null;
        
    public HmtxTable(TableRecord record)
    {
        super(record);
        hmtxData = new ArrayList();  
    }

    @Override
    public boolean read(BinaryMapReader file, TableList tables) {
        
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        HheaTable hheaTable = tables.getTable(HheaTable.class);
        if(!hheaTable.isRead())       
            hheaTable.parse(file, tables);
                  
        MaxpTable maxpTable = tables.getTable(MaxpTable.class);      
            maxpTable.parse(file, tables);
                      
        for (int i = 0; i < maxpTable.numGlyphs; i += 1) {
            // If the font is monospaced, only one entry is needed. This last entry applies to all subsequent glyphs.
            if (i < hheaTable.numOfHorMetrics) {
                LongHorMetricRecord lhMetric = new LongHorMetricRecord();
                lhMetric.advanceWidth = file.getUint16();
                lhMetric.lsb = file.getInt16();
                hmtxData.add(lhMetric);
            }
            else
            {
                //what if greater than num of horizontal metrics (check documentatation)
                //https://docs.microsoft.com/en-us/typography/opentype/spec/hmtx
                LongHorMetricRecord lhMetric = new LongHorMetricRecord();
                lhMetric.advanceWidth = hmtxData.get(hheaTable.numOfHorMetrics - 1).advanceWidth; //advance width is same to last long metric value
                lhMetric.lsb = file.getUint16();
                hmtxData.add(lhMetric);
            }
        }          
        return true;
    }
    
    public LongHorMetricRecord getLongHorMetricRecord(int glyphIndex)
    {
        return hmtxData.get(glyphIndex);
    }
    
    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return HMTX;
    }
}
