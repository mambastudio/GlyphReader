/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import glyphreader.TrueTypeFontInfo;
import glyphreader.fonts.notoserif.Resource;
import java.util.Arrays;

/**
 *
 * @author jmburu
 */
public class Test4 {
    public static void main(String... args)
    {
       TrueTypeFontInfo ttf = new TrueTypeFontInfo(Resource.class, "MaterialSymbolsOutlined.ttf");
       System.out.println(Arrays.toString(ttf.glyphNamesInfo()));       
    }
}
