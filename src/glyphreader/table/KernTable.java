/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.AbstractTable;
import glyphreader.read.BinaryMapReader;
import glyphreader.map.Kern0Table;
import static glyphreader.map.Table.TableType.KERN;
import glyphreader.map.TableList;
import glyphreader.read.BinaryReader;
import glyphreader.record.TableRecord;
import java.util.ArrayList;

/**
 *
 * @author jmburu
 */
public class KernTable extends AbstractTable{
    
    public ArrayList<Kern0Table> kernList;
    
    public KernTable(TableRecord record)
    {
        super(record);
        kernList = new ArrayList<>();
    }
    
    @Override
    public boolean read(BinaryReader file, TableList tables)
    { 
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        int version = file.getUint16(); // version 0
        int nTables = file.getUint16();
        
        System.out.format("Kern Table version: %s \n", version);
        System.out.format("Kern nTables: %s \n", nTables);
        for (int i = 0; i < nTables; i++) {
            version = file.getUint16(); // subtable version
            int length = file.getUint16();
            int coverage = file.getUint16();
            int format = coverage >> 8;
            int cross = coverage & 4;
            boolean vertical = (coverage & 0x1) == 0;
            System.out.format("Kerning subtable version %s format %s length %s coverage: %s",
                version, format, length, coverage);
            Kern0Table kern = null;
            if (format == 0) {
                kern = new Kern0Table(file, vertical, cross != 0);
            } else {
                System.out.format("Unknown format -- skip");
                file.seek(file.tell() + length);
            }
            if (kern != null) {
                this.kernList.add(kern);
            }
        }
        return true;
    }
    
    public Kern0Table getKern0Table(int index)
    {
        return kernList.get(index);
    }
    
    public int getKern0TableSize()
    {
        return kernList.size();
    }

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return KERN;
    }
}
