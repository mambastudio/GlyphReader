/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.read.BinaryMapReader;
import glyphreader.core.FBound;
import glyphreader.core.metrics.FHorizontalMetrics;
import glyphreader.map.CMap;
import glyphreader.map.Kern0Table;
import glyphreader.map.Table.TableType;
import glyphreader.map.TableDirectory;
import glyphreader.map.TableList;
import glyphreader.record.LongHorMetricRecord;
import glyphreader.table.CMapTable;
import glyphreader.table.GlyfTable;
import glyphreader.table.HeadTable;
import glyphreader.table.HmtxTable;
import glyphreader.table.KernTable;
import glyphreader.table.MaxpTable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author jmburu
 */
public final class TrueTypeFont {
    public BinaryMapReader file = null;  
    
    TableDirectory directory;
    TableList tables;
    
    public int length;
    
    public TrueTypeFont(Path path)
    {
        if(!Files.exists(path))
            throw new UnsupportedOperationException("File does not exist: " +path);
        this.file = new BinaryMapReader(path);
        
        //read the manifest of table
        this.directory = new TableDirectory(file);
        //read table offsetss
        this.tables = new TableList(directory);
        this.tables.parseTables();
                        
        length = glyphCount();
        
        //set horizontal metrics
        for(int index = 0; index<glyphCount(); index++)
        {
            Glyph glyph = tables.getTable(GlyfTable.class).getGlyph(index);
            if(glyph != null)
                glyph.setFHorizontalMetrics(this.getFontMetrics());
        }
        
        System.out.println(getTableNamesInTTF());
       
            
    }
    
    public Glyph getGlyph(int index)
    {
        FUtility.assertCheck(tables.containsTable(TableType.GLYF), "no glyf table");
        Glyph glyph = tables.getTable(GlyfTable.class).getGlyph(index);
        return glyph;
    }
        
    public int glyphCount() {
        FUtility.assertCheck(tables.containsTable(TableType.MAXP), "no maxp table");
        return tables.getTable(MaxpTable.class).numGlyphs;
    }   
    
    public double getUnitsPerEm()
    {
        FUtility.assertCheck(tables.containsTable(TableType.HEAD), "no head table");
        return tables.getTable(HeadTable.class).unitsPerEm;
    }
    
    public FBound getBound()
    {
        FUtility.assertCheck(tables.containsTable(TableType.HEAD), "no head table");
        return tables.getTable(HeadTable.class).getBound();       
    }
    
    public LongHorMetricRecord getLongHorMetricRecord(int glyphIndex)
    {
        FUtility.assertCheck(tables.containsTable(TableType.HMTX), "no hmtx table");
        return tables.getTable(HmtxTable.class).getLongHorMetricRecord(glyphIndex);
    }
    
    public Kern0Table getKern0Table(int glyphIndex)
    {
        FUtility.assertCheck(tables.containsTable(TableType.KERN), "no kern table");
        return tables.getTable(KernTable.class).getKern0Table(glyphIndex);
    }
    
    public int getKern0TableSize()
    {                
        if(tables.containsTable(TableType.KERN))
            return tables.getTable(KernTable.class).getKern0TableSize();
        else
            return 0;
    }
    
    public CMap getCMap(int index)
    {
        FUtility.assertCheck(tables.containsTable(TableType.CMAP), "no cmap table");
        return tables.getTable(CMapTable.class).cmaps.get(index);
    }
    
    public int getCMapSize()
    {
        FUtility.assertCheck(tables.containsTable(TableType.CMAP), "no kern table");
        return tables.getTable(CMapTable.class).cmaps.size();
    }
        
    public TableList getTableList()
    {
        return this.tables;
    }
    
    public ArrayList<String> getTableNamesInTTF()
    {
        ArrayList<String> tableNames = new ArrayList();
        tables.getRecords().forEach(record -> {
            tableNames.add(record.getName());
        });
        return tableNames;
    }
    
    public FHorizontalMetrics getFontMetrics()
    {
        return new FHorizontalMetrics(this);
    }
}
