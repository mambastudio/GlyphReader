/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.map.AbstractTable;
import static glyphreader.map.Table.TableType.MAXP;
import glyphreader.map.TableList;
import glyphreader.map.TableRecord;
import glyphreader.read.BinaryMapReader;

/**
 *
 * @author jmburu
 */
public class MaxpTable extends AbstractTable{
    
    public double version;
    public int numGlyphs;
    public int maxPoints;
    public int maxContours;
    public int maxCompositePoints;
    public int maxCompositeContours;
    public int maxZones;
    public int maxTwilightPoints;
    public int maxStorage;
    public int maxFunctionDefs;
    public int maxInstructionDefs;
    public int maxStackElements;
    public int maxSizeOfInstructions;
    public int maxComponentElements;
    public int maxComponentDepth;
    
    public MaxpTable(TableRecord record)
    {
        super(record);
    }
    @Override
    public boolean read(BinaryMapReader file, TableList tables) {
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        version = file.getVersion16Dot16();         
        if(version == 1.0)
        {
            numGlyphs = file.getUint16();
            maxPoints = file.getUint16();
            maxContours = file.getUint16();
            maxCompositePoints = file.getUint16();
            maxCompositeContours = file.getUint16();
            maxZones = file.getUint16();
            maxTwilightPoints = file.getUint16();
            maxStorage = file.getUint16();
            maxFunctionDefs = file.getUint16();
            maxInstructionDefs = file.getUint16();
            maxStackElements = file.getUint16();
            maxSizeOfInstructions = file.getUint16();
            maxComponentElements = file.getUint16();
            maxComponentDepth = file.getUint16();
        }        
        return true;
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
