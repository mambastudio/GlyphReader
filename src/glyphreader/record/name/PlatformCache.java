/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.record.name;

import glyphreader.enumtypes.PlatformIDEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jmburu
 */
public class PlatformCache {
    public Map<PlatformIDEnum, PlatformTypeAbstract> platforms;
    
    public PlatformCache()
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
    
        
    
    public PlatformTypeAbstract getPlatformTypeFromID(int id)
    {       
        return getPlatformType(PlatformIDEnum.forCode(id));
    }
  
    public PlatformTypeAbstract getFirstPlatformType()
    {
        PlatformIDEnum firstKey = (PlatformIDEnum) platforms.keySet().toArray()[0];
        return platforms.get(firstKey);
    }
    
    public PlatformTypeAbstract getPlatformType(PlatformIDEnum platformType)
    {
        return platforms.get(platformType);
    }
    
    public PlatformTypeAbstract getLastPlatformTypeRead()
    {
        PlatformIDEnum firstKey = (PlatformIDEnum) platforms.keySet().toArray()[platforms.size()-1];
        return platforms.get(firstKey);
    }
    
    public boolean hasPlatformType(int id)
    {
        return getPlatformTypeFromID(id) != null;
    }
    
    public void addPlatformType(int id)
    {
        addPlatformType(PlatformIDEnum.forCode(id));
    }
        
    public int getPlatformTypeSize()
    {
        return platforms.size();
    }
    
    public ArrayList<PlatformTypeAbstract> getAllPlatforms()
    {
        ArrayList<PlatformTypeAbstract> platformList = new ArrayList();
        platformList.addAll(platforms.values());
        return platformList;
    }
    
    public void addPlatformType(PlatformIDEnum platformType)
    {        
        if(null != platformType)
            switch (platformType) {
            case Windows:
                if(!platforms.containsKey(PlatformIDEnum.Windows))                
                    platforms.put(platformType, new PlatformTypeWindows()); 
                break;
            case Macintosh:
                if(!platforms.containsKey(PlatformIDEnum.Macintosh))                
                    platforms.put(platformType, new PlatformTypeMacintosh());
                break;
            case Unicode:
                if(!platforms.containsKey(PlatformIDEnum.Unicode))                
                    platforms.put(platformType, new PlatformTypeUnicode());
                break;
            default:
                break;
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        platforms.keySet().forEach(platformEnum -> {
            builder.append(fixedLengthString("Platform: ", 22)).append(platformEnum).append("\n");
            builder.append(platforms.get(platformEnum)).append("\n");
        });
        //https://stackoverflow.com/questions/6652687/strip-leading-and-trailing-spaces-from-java-string
        return builder.toString().replaceAll("\\s+$", "");
    }
    
    //https://stackoverflow.com/questions/13475388/generate-fixed-length-strings-filled-with-whitespaces
    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
}
