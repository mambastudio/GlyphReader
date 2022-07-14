/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import glyphreader.read.BinaryMapReader;
import glyphreader.core.FMatrix;
import glyphreader.core.FPoint2d;
import glyphreader.core.FBound;
import glyphreader.core.FPoint2i;
import glyphreader.map.CMap;
import glyphreader.map.Table;
import glyphreader.map.Table.TableType;
import glyphreader.map.TableList;
import glyphreader.table.CMapTable;
import glyphreader.table.HeadTable;
import glyphreader.table.HheaTable;
import glyphreader.table.NameTable;
import glyphreader.map.TableRecord;
import glyphreader.table.HmtxTable;
import glyphreader.table.KernTable;
import glyphreader.table.MaxpTable;
import glyphreader.table.PostTable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author jmburu
 */
public final class TrueTypeFont {
    private BinaryMapReader file = null;
    
    TableList tables = null;
    
    public int scalarType = 0;
    public int searchRange = 0;
    public int entrySelector = 0;
    public int rangeShift = 0;
  
    
    public int length;
    
    public TrueTypeFont(Path path)
    {
        if(!Files.exists(path))
            throw new UnsupportedOperationException("File does not exist: " +path);
        this.file = new BinaryMapReader(path);
        this.tables = new TableList();
                
        readOffsetTables();
        
        headTable.read(file, tables); 
        nameTable.read(file, tables); 
        cmapTable.read(file, tables); 
        hheaTable.read(file, tables); 
        kernTable.read(file, tables); 
        postTable.read(file, tables); 
        hmtxTable.read(file, tables); 
        
        length = glyphCount();
        System.out.println("glyph count " +length);
    }
    
    public void readOffsetTables()
    {
        
        scalarType = file.getUint32();
        int numTables = file.getUint16();
        
        this.searchRange = file.getUint16();
        this.entrySelector = file.getUint16();
        this.rangeShift = file.getUint16();
        
        for(int i = 0 ; i < numTables; i++ ) {
            //create table
            String tag = file.getString(4);
            Table table = this.generateTable(tag);
            tables.addTable(table);
            
            TableRecord recordTable = table.getRecord();
            recordTable.checksum = file.getUint32();
            recordTable.offset = file.getUint32();
            recordTable.length = file.getUint32();
                        
            if(!tag.contains("head"))
            {
                //System.out.println("TableRecord " +tag+ " has checksum " +table.checksum);
                //Utility.assertCheck((calculateTableChecksum(file, ctables.get(tag).offset, ctables.get(tag).length) == 
                //       ctables.get(tag).checksum));
            }
        }
        
        
        
    }
    
    public Table generateTable(String string)
    {
        if(string.equals(TableType.CMAP.name().toLowerCase()))
            return new CMapTable();
        else if(string.equals(TableType.HEAD.name().toLowerCase()))
            return new HeadTable();
        else if(string.equals(TableType.HHEA.name().toLowerCase()))
            return new HheaTable();
        else if(string.equals(TableType.HMTX.name().toLowerCase()))
            return new HmtxTable();
        else if(string.equals(TableType.KERN.name().toLowerCase()))
            return new KernTable();
        else if(string.equals(TableType.MAXP.name().toLowerCase()))
            return new MaxpTable();
        else if(string.equals(TableType.NAME.name().toLowerCase()))
            return new NameTable();
        else if(string.equals(TableType.POST.name().toLowerCase()))
            return new PostTable();
        return null;
    }
    
    //to verify if the unsigned values are read correct
    public int calculateTableChecksum(BinaryMapReader file, int offset, int length)
    {
        int old = file.seek(offset);
        int sum = 0;
        int nlongs = ((length + 3) / 4);
        while(nlongs>0 ) {
            sum = (sum + file.getUint32() & 0xffffffff);
            nlongs--;
        }
        
        file.seek(old);
        return sum;
    }
    
    public int glyphCount() {
        FUtility.assertCheck(tables.containsKey("maxp"), "no maxp");
        int old = this.file.seek(tables.get("maxp").offset + 4);
        int count = this.file.getUint16();
        this.file.seek(old);
        return count;
    }
    
    public int getGlyphOffset(int index) {
        FUtility.assertCheck(tables.containsKey("loca"));
        TableRecord table = tables.get("loca");
        
        int offset, old, next;        
        if (headTable.indexToLocFormat == 1) {
            old = file.seek(table.offset + index * 4);
            offset = file.getUint32();
            next = file.getUint32();
        } else {            
            old = file.seek(table.offset + index * 2);
            offset = file.getUint16() * 2;
            next = file.getUint16() * 2;
        }

        file.seek(old);
        if (offset == next) {
            // indicates glyph has no outline( eg space)
            return 0;
        }

        //this.log("Offset for glyph index %s is %s", index, offset);

        return offset + this.tables.get("glyf").offset;
    }
    
    public Glyph readGlyph(int index)
    {
        int offset = this.getGlyphOffset(index);
        
        if (offset == 0 ||
            offset >= this.tables.get("glyf").offset + this.tables.get("glyf").length) {
            return null;
        }

        FUtility.assertCheck(offset >= this.tables.get("glyf").offset);
        FUtility.assertCheck(offset < this.tables.get("glyf").offset + this.tables.get("glyf").length);

        file.seek(offset);
        
        Glyph glyph = new Glyph();        
        glyph.numberOfContours = file.getInt16();
        glyph.bound.xMin = file.getFword();
        glyph.bound.yMin = file.getFword();
        glyph.bound.xMax = file.getFword();
        glyph.bound.yMax = file.getFword();
        

        FUtility.assertCheck(glyph.numberOfContours >= -1);

        if (glyph.numberOfContours == -1) {
            this.readCompoundGlyph(glyph);
        } else {
            this.readSimpleGlyph(glyph);
        }

        return glyph;
    }
    
    public void readCoords(char name, int byteFlag,
            int deltaFlag, double min, double max, 
            int numPoints, ArrayList<Integer> flags, ArrayList<FPoint2d> points) {
        int  value = 0;

        for (int i = 0; i < numPoints; i++) {
            int flag = flags.get(i);
            if ((flag & byteFlag) > 0) {
                if ((flag & deltaFlag) > 0) {
                    value += file.getUint8();
                } else {
                    value -= file.getUint8();
                }
            } else if ((~flag & deltaFlag) > 0) {
                value += file.getInt16();
            } else {
                // value is unchanged.
            }

            points.get(i).set(name, value);
        }
    }
    
    public void readSimpleGlyph(Glyph glyph) {
        int ON_CURVE = 1,
            X_IS_BYTE = 2,
            Y_IS_BYTE = 4,
            REPEAT = 8,
            X_DELTA = 16,
            Y_DELTA = 32;

        glyph.contourEnds = new ArrayList<>();
        ArrayList<FPoint2d> points = glyph.points;

        for (int i = 0; i < glyph.numberOfContours; i++) {
            glyph.contourEnds.add(file.getUint16());
        }

        // skip over intructions
        file.seek(file.getUint16() + file.tell());

        if (glyph.numberOfContours == 0) {
            return;
        }

        int numPoints = Collections.max(glyph.contourEnds) + 1;

        ArrayList<Integer> flags = new ArrayList<>();

        for (int i = 0; i < numPoints; i++) {
            int flag = file.getUint8();
            flags.add(flag); 
            points.add(new FPoint2d(0, 0, (flag & ON_CURVE) > 0));

            if ((flag & REPEAT)>0) {
                int repeatCount = file.getUint8();
                FUtility.assertCheck(repeatCount > 0);
                i += repeatCount;
                while ((repeatCount--)>0) {
                    flags.add(flag);
                    points.add(new FPoint2d(0,0,(flag & ON_CURVE) > 0));
                }
            }
        }

        readCoords('x', X_IS_BYTE, X_DELTA, glyph.bound.xMin, glyph.bound.xMax, numPoints, flags, points);
        readCoords('y', Y_IS_BYTE, Y_DELTA, glyph.bound.yMin, glyph.bound.yMax, numPoints, flags, points);
    }
    
    public void readCompoundGlyph(Glyph glyph) {
        int ARG_1_AND_2_ARE_WORDS = 1,
            ARGS_ARE_XY_VALUES = 2,
            ROUND_XY_TO_GRID = 4,
            WE_HAVE_A_SCALE = 8,
            // RESERVED              = 16
            MORE_COMPONENTS = 32,
            WE_HAVE_AN_X_AND_Y_SCALE = 64,
            WE_HAVE_A_TWO_BY_TWO = 128,
            WE_HAVE_INSTRUCTIONS = 256,
            USE_MY_METRICS = 512,
            OVERLAP_COMPONENT = 1024;
        
        int flags = MORE_COMPONENTS;
        Component component;
        glyph.contourEnds = new ArrayList();
        glyph.points = new ArrayList();
        while ((flags & MORE_COMPONENTS) > 0) {
            int arg1, arg2;

            flags = file.getUint16();
            
            component = new Component();
            component.glyphIndex = file.getUint16();
            component.matrix = new FMatrix();
            component.destPointIndex = 0;
            component.srcPointIndex = 0;

            if ((flags & ARG_1_AND_2_ARE_WORDS) > 0) {
                arg1 = file.getInt16();
                arg2 = file.getInt16();
            } else {
                arg1 = file.getUint8();
                arg2 = file.getUint8();
            }

            if ((flags & ARGS_ARE_XY_VALUES) > 0) {
                component.matrix.e = arg1;
                component.matrix.f = arg2;
            } else {
                component.destPointIndex = arg1;
                component.srcPointIndex = arg2;
            }

            if ((flags & WE_HAVE_A_SCALE) > 0) {
                component.matrix.a = file.get2Dot14();
                component.matrix.d = component.matrix.a;
            } else if ((flags & WE_HAVE_AN_X_AND_Y_SCALE) > 0) {
                component.matrix.a = file.get2Dot14();
                component.matrix.d = file.get2Dot14();
            } else if ((flags & WE_HAVE_A_TWO_BY_TWO) > 0) {
                component.matrix.a = file.get2Dot14();
                component.matrix.b = file.get2Dot14();
                component.matrix.c = file.get2Dot14();
                component.matrix.d = file.get2Dot14();
            }

            //System.out.format("Read component glyph index %s", component.glyphIndex);
            //System.out.format("Transform: [%s %s %s %s %s %s]", component.matrix.a, component.matrix.b,
            //    component.matrix.c, component.matrix.d, component.matrix.e, component.matrix.f);
            int old = file.tell();
            Glyph simpleGlyph = this.readGlyph(component.glyphIndex);
            if (simpleGlyph != null) {
                int pointOffset = glyph.points.size();
                for (int i = 0; i < simpleGlyph.contourEnds.size(); i++) {
                    glyph.contourEnds.add(simpleGlyph.contourEnds.get(i) +
                        pointOffset);
                }
                for (int i = 0; i < simpleGlyph.points.size(); i++) {
                    double x = simpleGlyph.points.get(i).x;
                    double y = simpleGlyph.points.get(i).y;
                    x = component.matrix.a * x + component.matrix.b * y +
                        component.matrix.e;
                    y = component.matrix.c * x + component.matrix.d * y +
                        component.matrix.f;
                    glyph.points.add(new FPoint2d(x, y, simpleGlyph.points.get(i).onCurve));
                }
            }

            file.seek(old);
        }

        glyph.numberOfContours = glyph.contourEnds.size();

        if ((flags & WE_HAVE_INSTRUCTIONS) > 0) {
            file.seek(file.getUint16() + file.tell());
        }
        
    }
    
    public FBound getBound()
    {
        return new FBound(headTable.xMin, headTable.yMin, headTable.xMax, headTable.yMax);
    }
    
    public FPoint2i getHorizontalMetrics(int glyphIndex) 
    {                
        if(tables.containsKey("hmtx"))
        {
            int old = file.seek(this.tables.get("hmtx").offset + 4);
            int offset = this.tables.get("hmtx").offset;
            int advanceWidth, leftSideBearing;
            if (glyphIndex < this.hheaTable.numOfHorMetrics) {
                offset += glyphIndex * 4;
                old = this.file.seek(offset);
                advanceWidth = file.getUint16();
                leftSideBearing = file.getInt16();
            } else {
                // read the last entry of the hMetrics array
                old = file.seek(offset + (this.hheaTable.numOfHorMetrics - 1) * 4);
                advanceWidth = file.getUint16();
                file.seek(offset + this.hheaTable.numOfHorMetrics * 4 +
                    2 * (glyphIndex - this.hheaTable.numOfHorMetrics));
                leftSideBearing = file.getFword();
            }
 
            this.file.seek(old);
            return new FPoint2i(advanceWidth, leftSideBearing); 
        }
        return null;
    }
}
