/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import glyphreader.map.Table;
import glyphreader.map.TableDirectory;
import glyphreader.map.TableList;
import glyphreader.map.TableRecord;
import glyphreader.read.BinaryMapReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author user
 */
public class Test2 {
    public static void main(String... args)
    {
        Path path = Paths.get("C:\\Users\\user\\Downloads\\PT_Serif", "PTSerif-Regular.ttf");
        BinaryMapReader file;
        if(!Files.exists(path))
            throw new UnsupportedOperationException("File does not exist: " +path);
        file = new BinaryMapReader(path);
        
        TableDirectory directory = new TableDirectory(file);
        TableList tables = new TableList(directory);
        tables.parseTables();
        
        
        
    }
}
