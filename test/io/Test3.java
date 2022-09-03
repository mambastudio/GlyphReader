/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import glyphreader.TrueTypeFont;
import glyphreader.fonts.notoserif.Resource;
import glyphreader.map.AbstractTable;
import glyphreader.map.Table;
import glyphreader.map.TableList;

/**
 *
 * @author jmburu
 */
public class Test3 {
    public static void main(String... args)
    {
        TrueTypeFont ttf = new TrueTypeFont(Resource.class, "MaterialIcons-Regular.ttf");
        TableList tables = ttf.getTableList();
        AbstractTable names = tables.getTable(Table.TableType.NAME);
        System.out.println(names);
    }
}
