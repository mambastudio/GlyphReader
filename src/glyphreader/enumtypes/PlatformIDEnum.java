/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glyphreader.enumtypes;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author jmburu
 * 
 * https://stackoverflow.com/questions/14319232/get-enum-name-from-enum-value
 * 
 */
public enum PlatformIDEnum{
    Unicode(0), 
    Macintosh(1), 
    Windows(3);

    private final int code; 
    private PlatformIDEnum(int code)
    {
        this.code = code;
    }

    //store the enums in the following map
    private static final Map<Integer, PlatformIDEnum> BY_CODE_MAP = new LinkedHashMap<>();
    //automatic call to store enums in map
    static {
        for (PlatformIDEnum platformID : PlatformIDEnum.values()) {
            BY_CODE_MAP.put(platformID.code, platformID);
        }
    }

    //scour through the available platform based on code
    public static PlatformIDEnum forCode(int code) {
        
        return BY_CODE_MAP.get(code);
    }
};
