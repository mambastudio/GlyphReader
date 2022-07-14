/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.Table;
import static glyphreader.map.Table.TableType.MAXP;
import glyphreader.map.TableList;
import glyphreader.map.TableRecord;
import glyphreader.read.BinaryMapReader;

/**
 *
 * @author jmburu
 */
public class MaxpTable implements Table{
    private final TableRecord record;
    
    public MaxpTable()
    {
        record = new TableRecord();
    }
    @Override
    public void read(BinaryMapReader file, TableList tables) {
        
    }

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return MAXP;
    }
    
}
