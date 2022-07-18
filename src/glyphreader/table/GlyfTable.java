/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.table;

import glyphreader.Component;
import glyphreader.FUtility;
import glyphreader.Glyph;
import glyphreader.core.FMatrix;
import glyphreader.core.FPoint2d;
import glyphreader.map.AbstractTable;
import static glyphreader.map.Table.TableType.GLYF;
import glyphreader.map.TableList;
import glyphreader.record.TableRecord;
import glyphreader.read.BinaryMapReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author user
 * 
 * 
 * All glyph information is here based on true type format
 * 
 * Horizontal metrics information is contained in HmtxTable (could transferred to Glyph class if needed, but must create relevant variables)
 * 
 * Vertical metrics information not supported yet. Might be in future.
 * 
 */
public class GlyfTable extends AbstractTable{

    private final ArrayList<Glyph> glyphs;
    
    public GlyfTable(TableRecord record)
    {
        super(record);       
        glyphs = new ArrayList();
    }
    
    @Override
    public boolean read(BinaryMapReader file, TableList tables) {
        int tableOffset = record.offset;
        file.seek(tableOffset);
        
        //read maxp table if not read
        MaxpTable maxp = tables.getTable(MaxpTable.class);
        if(!maxp.isRead())
            maxp.parse(file, tables);
        
        //read loca table if not read
        LocaTable loca = tables.getTable(LocaTable.class);
        if(!loca.isRead())
            loca.parse(file, tables);
        
        for(int index = 0; index<maxp.numGlyphs; index++)
        {
            Glyph glyph = readGlyph(file, tables, index);
            glyphs.add(glyph);            
        }
        
        return true;
    }
    
    

    @Override
    public TableRecord getRecord() {
        return record;
    }

    @Override
    public TableType getType() {
        return GLYF;
    }
    
    public Glyph getGlyph(int index)
    {
        return glyphs.get(index);
    }
    
    private void readCoords(
            BinaryMapReader file, 
            char coord, 
            int byteFlag,
            int deltaFlag, 
            int numPoints, 
            ArrayList<Integer> flags, 
            ArrayList<FPoint2d> points) {
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

            points.get(i).set(coord, value);
        }
    }
    
    private int getGlyphOffset(TableList tables, int index) {
               
        FUtility.assertCheck(tables.containsTable(TableType.LOCA));                 
        return tables.getTable(LocaTable.class).getGlyphOffset(index);
    }
    
    private Glyph readGlyph(BinaryMapReader file, TableList tables, int index)
    {
        int offset = this.getGlyphOffset(tables, index);
        
        if (offset == 0 ||
            offset >= tables.getTableRecord(TableType.GLYF).offset + tables.getTableRecord(TableType.GLYF).length) {
            return null;
        }

        FUtility.assertCheck(offset >= tables.getTableRecord(TableType.GLYF).offset);
        FUtility.assertCheck(offset < tables.getTableRecord(TableType.GLYF).offset + tables.getTableRecord(TableType.GLYF).length);

        file.seek(offset);
        
        Glyph glyph = new Glyph();        
        glyph.numberOfContours = file.getInt16();
        glyph.bound.xMin = file.getFword();
        glyph.bound.yMin = file.getFword();
        glyph.bound.xMax = file.getFword();
        glyph.bound.yMax = file.getFword();
        

        FUtility.assertCheck(glyph.numberOfContours >= -1);

        if (glyph.numberOfContours == -1) {
            this.readCompoundGlyph(tables, file, glyph);
        } else {
            this.readSimpleGlyph(file, glyph);
        }

        return glyph;
    }
    
    private void readSimpleGlyph(BinaryMapReader file, Glyph glyph) {
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

        readCoords(file, 'x', X_IS_BYTE, X_DELTA, numPoints, flags, points);
        readCoords(file, 'y', Y_IS_BYTE, Y_DELTA, numPoints, flags, points);
    }
    
    private void readCompoundGlyph(TableList tables, BinaryMapReader file, Glyph glyph) {
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
            Glyph simpleGlyph = this.readGlyph(file, tables, component.glyphIndex);
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
}
