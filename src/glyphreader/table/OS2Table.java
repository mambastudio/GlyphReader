/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.AbstractTable;
import glyphreader.map.TableList;
import glyphreader.read.BinaryReader;
import glyphreader.record.TableRecord;

/**
 *
 * @author jmburu
 */
public class OS2Table  extends AbstractTable  {

    public int version;
    public int xAvgCharWidth;
    public int usWeightClass;
    public int usWidthClass;
    public int fsType;
    
    public OS2Table(TableRecord record)
    {
        super(record);
    }
    
    @Override
    public boolean read(BinaryReader file, TableList tables) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TableType getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
