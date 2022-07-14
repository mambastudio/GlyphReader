/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

/**
 *
 * @author jmburu
 */
public class HmtxData {
    public int advanceWidth;
    public int leftSideBearing;
    
    public HmtxData()
    {
        advanceWidth = leftSideBearing = 0;
    }
    
    public HmtxData(int advanceWidth, int leftSideBearing)
    {
        this.advanceWidth = advanceWidth;
        this.leftSideBearing = leftSideBearing;
    }
}
