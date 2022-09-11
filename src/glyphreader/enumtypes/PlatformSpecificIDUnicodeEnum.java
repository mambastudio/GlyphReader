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
 */

public enum PlatformSpecificIDUnicodeEnum implements PlatformSpecificAbstractEnum{
    Unicode_1_0(0), 
    Unicode_1_1(1), 
    ISO_OR_IEC_10646 (2),
    Unicode_2_0_BMP(3),
    Unicode_2_0_Full_Repertoire(4);

    private final int code; 
    private PlatformSpecificIDUnicodeEnum(int code)
    {
        this.code = code;
    }

    //store the enums in the following map
    private static final Map<Integer, PlatformSpecificIDUnicodeEnum> BY_CODE_MAP = new LinkedHashMap<>();
    //automatic call to store enums in map
    static {
        for (PlatformSpecificIDUnicodeEnum platformSpecificID : PlatformSpecificIDUnicodeEnum.values()) {
            BY_CODE_MAP.put(platformSpecificID.code, platformSpecificID);
        }
    }

    //scour through the available platform based on code
    public static PlatformSpecificIDUnicodeEnum forCode(int code) {
        return BY_CODE_MAP.get(code);
    }
};
    

