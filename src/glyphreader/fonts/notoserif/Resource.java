/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.fonts.notoserif;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmburu
 */
public class Resource {
    public static InputStream getInputStream(Class<?> clazz, String file)
    {
        return clazz.getResourceAsStream(file);         
    }
    
    private static String readFromInputStream(InputStream inputStream)
    {
        StringBuilder resultStringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultStringBuilder.toString();
    }
}
