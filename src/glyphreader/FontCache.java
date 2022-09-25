/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.prefs.Preferences;

/**
 *
 * @author jmburu
 */
public final class FontCache {
    private static enum OSType{Windows, MacOS, Linux, Other};
    
    private static Preferences prefs = null;        
    private static OSType detectedOS = null;
    private static Map<String, TrueTypeFontInfo> fontListSystemInfo = null;
    
    private FontCache()
    {
        
    }
        
    /**
   * detect the operating system from the os.name System property and cache
   * the result
   * 
   * @returns - the operating system detected
   */
    private static OSType getOperatingSystemType() {
      if (detectedOS == null) {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.contains("mac")) || (OS.contains("darwin"))) {
          detectedOS = OSType.MacOS;
        } else if (OS.contains("win")) {
          detectedOS = OSType.Windows;
        } else if (OS.contains("nux")) {
          detectedOS = OSType.Linux;
        } else {
          detectedOS = OSType.Other;
        }
      }
      return detectedOS;
    }
    
    private static OSType getDetectedOS()
    {
        if(detectedOS == null)
            return getOperatingSystemType();
        else
            return detectedOS;
    }
    
    private static boolean isWindows()
    {
        return getDetectedOS() == OSType.Windows;
    }
    
    private static void initPlatformFonts()
    {
        if(isWindows())
        {
            String path = System.getenv("WINDIR");
            File fontDirectory = new File(path, "Fonts");
            
            File[] fontFiles = fontDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".ttf"));
            if(fontListSystemInfo == null)
                fontListSystemInfo = new HashMap();
            
            for(File fontFile : fontFiles)
            {                
                TrueTypeFontInfo fontInfo = new TrueTypeFontInfo(fontFile.toPath());
                fontListSystemInfo.put(fontInfo.getFontFullName(), fontInfo);
            }
        }  
    }
    
    public static TrueTypeFontInfo getTTFInfo(String fullName)
    {
        if(fontListSystemInfo == null)
            initPlatformFonts();
        return fontListSystemInfo.getOrDefault(fullName, null);
    }
    
    public static List<TrueTypeFontInfo> getSystemTTFInfo()
    {
        if(fontListSystemInfo == null)
            initPlatformFonts();
        List<TrueTypeFontInfo> list = new ArrayList();
        list.addAll(fontListSystemInfo.values());
        return list;
    }
}
