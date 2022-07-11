/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.map;

import glyphreader.read.BinaryMapReader;
import java.util.HashMap;

/**
 *
 * @author user
 */
public interface Table {
    public void read(BinaryMapReader file, HashMap<String, TableRecord> tables);
}
