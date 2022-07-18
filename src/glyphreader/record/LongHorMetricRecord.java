/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record;

/**
 *
 * @author user
 */
public class LongHorMetricRecord {
    public int advanceWidth;
    public int lsb;
    
    public LongHorMetricRecord copy()
    {
        LongHorMetricRecord record = new LongHorMetricRecord();
        record.advanceWidth = advanceWidth;
        record.lsb = lsb;
        return record;
    }
}
