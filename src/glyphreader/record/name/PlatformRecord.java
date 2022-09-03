/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record.name;

import glyphreader.enumtypes.PlatformIDEnum;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jmburu
 */
public class PlatformRecord {
    public Map<PlatformIDEnum, PlatformTypeSkeleton> platforms;
    
    public PlatformRecord()
    {
        platforms = new HashMap();
    }
    
    public boolean hasWindows()
    {
        return platforms.containsKey(PlatformIDEnum.Windows);
    }
    
    public boolean hasMacintosh()
    {
        return platforms.containsKey(PlatformIDEnum.Macintosh);
    }
    
    public boolean hasUnicode()
    {
        return platforms.containsKey(PlatformIDEnum.Unicode);
    }
    
    public boolean hasMultiplePlatforms()
    {
        return platforms.size() > 1;
    }
        
    public void addPlatform(PlatformIDEnum platformType)
    {
        if(null != platformType)
            switch (platformType) {
            case Windows:
                platforms.put(platformType, new PlatformWindowRecord());
                break;
            case Macintosh:
                platforms.put(platformType, new PlatformMacintoshRecord());
                break;
            case Unicode:
                platforms.put(platformType, new PlatformUnicodeRecord());
                break;
            default:
                break;
        }
    }
}
