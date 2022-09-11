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
public enum LanguageIDsWindowsEnum implements LanguageIDsPlatformAbstractEnum {   
    Afghanistan_Dari(Integer.decode("0x048C"),"Dari","Afghanistan"),
    Afghanistan_Pashto(Integer.decode("0x0463"),"Pashto","Afghanistan"),
    Albania(Integer.decode("0x041C"),"Albanian","Albania"),
    Algeria_Arabic(Integer.decode("0x1401"),"Arabic","Algeria"),
    Algeria_Tamazight(Integer.decode("0x085F"),"Tamazight (Latin)","Algeria"),
    Argentina(Integer.decode("0x2C0A"),"Spanish","Argentina"),
    Armenia(Integer.decode("0x042B"),"Armenian","Armenia"),
    Australia(Integer.decode("0x0C09"),"English","Australia"),
    Austria(Integer.decode("0x0C07"),"German","Austria"),
    Azerbaijan_Cyrillic(Integer.decode("0x082C"),"Azeri (Cyrillic)","Azerbaijan"),
    Azerbaijan_Latin(Integer.decode("0x042C"),"Azeri (Latin)","Azerbaijan"),
    Bahrain(Integer.decode("0x3C01"),"Arabic","Bahrain"),
    Bangladesh(Integer.decode("0x0845"),"Bengali","Bangladesh"),
    Basque(Integer.decode("0x042D"),"Basque","Basque"),
    Belarus(Integer.decode("0x0423"),"Belarusian","Belarus"),
    Belgium_Dutch(Integer.decode("0x0813"),"Dutch","Belgium"),
    Belgium_French(Integer.decode("0x080C"),"French","Belgium"),
    Belize(Integer.decode("0x2809"),"English","Belize"),
    Bolivia_Quechua(Integer.decode("0x046B"),"Quechua","Bolivia"),
    Bolivia_Spanish(Integer.decode("0x400A"),"Spanish","Bolivia"),
    Bosnia_and_Herzegovina_Croatian(Integer.decode("0x101A"),"Croatian (Latin)","Bosnia and Herzegovina"),
    Bosnia_and_Herzegovina_Cyrillic(Integer.decode("0x1C1A"),"Serbian (Cyrillic)","Bosnia and Herzegovina"),
    Bosnia_and_Herzegovina_Latin(Integer.decode("0x181A"),"Serbian (Latin)","Bosnia and Herzegovina"),
    Bosnia_Herzegovina_Cyrillic(Integer.decode("0x201A"),"Bosnian (Cyrillic)","Bosnia and Herzegovina"),
    Bosnia_Herzegovina_Latin(Integer.decode("0x141A"),"Bosnian (Latin)","Bosnia and Herzegovina"),
    Brazil(Integer.decode("0x0416"),"Portuguese","Brazil"),
    Brunei_Darussalam(Integer.decode("0x083E"),"Malay","Brunei Darussalam"),
    Bulgaria(Integer.decode("0x0402"),"Bulgarian","Bulgaria"),
    Cambodia(Integer.decode("0x0453"),"Khmer","Cambodia"),
    Canada_English(Integer.decode("0x1009"),"English","Canada"),
    Canada_French(Integer.decode("0x0C0C"),"French","Canada"),
    Canada_Inuktitut(Integer.decode("0x045D"),"Inuktitut","Canada"),
    Canada_Inuktitut_Latin(Integer.decode("0x085D"),"Inuktitut (Latin)","Canada"),
    Caribbean(Integer.decode("0x2409"),"English","Caribbean"),
    Catalan(Integer.decode("0x0403"),"Catalan","Catalan"),
    Chile_Mapudungun(Integer.decode("0x047A"),"Mapudungun","Chile"),
    Chile_Spanish(Integer.decode("0x340A"),"Spanish","Chile"),
    Colombia(Integer.decode("0x240A"),"Spanish","Colombia"),
    Costa_Rica(Integer.decode("0x140A"),"Spanish","Costa Rica"),
    Croatia(Integer.decode("0x041A"),"Croatian","Croatia"),
    Czech_Republic(Integer.decode("0x0405"),"Czech","Czech Republic"),
    Denmark(Integer.decode("0x0406"),"Danish","Denmark"),
    Dominican_Republic(Integer.decode("0x1C0A"),"Spanish","Dominican Republic"),
    Ecuador_Quechua(Integer.decode("0x086B"),"Quechua","Ecuador"),
    Ecuador_Spanish(Integer.decode("0x300A"),"Spanish","Ecuador"),
    Egypt(Integer.decode("0x0C01"),"Arabic","Egypt"),
    El_Salvador(Integer.decode("0x440A"),"Spanish","El Salvador"),
    Estonia(Integer.decode("0x0425"),"Estonian","Estonia"),
    Ethiopia(Integer.decode("0x045E"),"Amharic","Ethiopia"),
    Faroe_Islands(Integer.decode("0x0438"),"Faroese","Faroe Islands"),
    Finland_Finnish(Integer.decode("0x040B"),"Finnish","Finland"),
    Finland_Swedish(Integer.decode("0x081D"),"Swedish","Finland"),
    Finland_Inari(Integer.decode("0x243B"),"Sami (Inari)","Finland"),
    Finland_Northern(Integer.decode("0x0C3B"),"Sami (Northern)","Finland"),
    Finland_Skolt(Integer.decode("0x203B"),"Sami (Skolt)","Finland"),
    France_Alsatian(Integer.decode("0x0484"),"Alsatian","France"),
    France_French(Integer.decode("0x040C"),"French","France"),
    France_Occitan(Integer.decode("0x0482"),"Occitan","France"),
    France_Breton(Integer.decode("0x047E"),"Breton","France"),
    France_Corsican(Integer.decode("0x0483"),"Corsican","France"),
    Galician(Integer.decode("0x0456"),"Galician","Galician"),
    Georgia(Integer.decode("0x0437"),"Georgian","Georgia"),
    Germany_German(Integer.decode("0x0407"),"German","Germany"),
    Germany_Lower_Sorbian(Integer.decode("0x082E"),"Lower Sorbian","Germany"),
    Germany_Upper_Sorbian(Integer.decode("0x042E"),"Upper Sorbian","Germany"),
    Greece(Integer.decode("0x0408"),"Greek","Greece"),
    Greenland(Integer.decode("0x046F"),"Greenlandic","Greenland"),
    Guatemala_Kiche(Integer.decode("0x0486"),"K’iche","Guatemala"),
    Guatemala_Spanish(Integer.decode("0x100A"),"Spanish","Guatemala"),
    Honduras_Spanish(Integer.decode("0x480A"),"Spanish","Honduras"),
    Hong_Kong_SAR(Integer.decode("0x0C04"),"Chinese","Hong Kong S.A.R."),
    Hungary(Integer.decode("0x040E"),"Hungarian","Hungary"),
    Iceland(Integer.decode("0x040F"),"Icelandic","Iceland"),
    India_Kannada(Integer.decode("0x044B"),"Kannada","India"),
    India_Konkani(Integer.decode("0x0457"),"Konkani","India"),
    India_Malayalam(Integer.decode("0x044C"),"Malayalam","India"),
    India_Assamese(Integer.decode("0x044D"),"Assamese","India"),
    India_Bengali(Integer.decode("0x0445"),"Bengali","India"),
    India_English(Integer.decode("0x4009"),"English","India"),
    India_Gujarati(Integer.decode("0x0447"),"Gujarati","India"),
    India_Hindi(Integer.decode("0x0439"),"Hindi","India"),
    India_Marathi(Integer.decode("0x044E"),"Marathi","India"),
    India_Odia(Integer.decode("0x0448"),"Odia (formerly Oriya)","India"),
    India_Punjabi(Integer.decode("0x0446"),"Punjabi","India"),
    India_Sanskrit(Integer.decode("0x044F"),"Sanskrit","India"),
    India_Tamil(Integer.decode("0x0449"),"Tamil","India"),
    India_Telugu(Integer.decode("0x044A"),"Telugu","India"),
    Indonesia(Integer.decode("0x0421"),"Indonesian","Indonesia"),
    Iraq(Integer.decode("0x0801"),"Arabic","Iraq"),
    Ireland_English(Integer.decode("0x1809"),"English","Ireland"),
    Ireland_Irish(Integer.decode("0x083C"),"Irish","Ireland"),
    Islamic_Republic_of_Pakistan(Integer.decode("0x0420"),"Urdu","Islamic Republic of Pakistan"),
    Israel(Integer.decode("0x040D"),"Hebrew","Israel"),
    Italy(Integer.decode("0x0410"),"Italian","Italy"),
    Jamaica(Integer.decode("0x2009"),"English","Jamaica"),
    Japan(Integer.decode("0x0411"),"Japanese","Japan"),
    Jordan(Integer.decode("0x2C01"),"Arabic","Jordan"),
    Kazakhstan(Integer.decode("0x043F"),"Kazakh","Kazakhstan"),
    Kenya(Integer.decode("0x0441"),"Kiswahili","Kenya"),
    Korea(Integer.decode("0x0412"),"Korean","Korea"),
    Kuwait(Integer.decode("0x3401"),"Arabic","Kuwait"),
    Kyrgyzstan(Integer.decode("0x0440"),"Kyrgyz","Kyrgyzstan"),
    Lao_PDR(Integer.decode("0x0454"),"Lao","Lao P.D.R."),
    Latvia(Integer.decode("0x0426"),"Latvian","Latvia"),
    Lebanon(Integer.decode("0x3001"),"Arabic","Lebanon"),
    Libya(Integer.decode("0x1001"),"Arabic","Libya"),
    Liechtenstein(Integer.decode("0x1407"),"German","Liechtenstein"),
    Lithuania(Integer.decode("0x0427"),"Lithuanian","Lithuania"),
    Luxembourg_French(Integer.decode("0x140c"),"French","Luxembourg"),
    Luxembourg_German(Integer.decode("0x1007"),"German","Luxembourg"),
    Luxembourg_Luxembourgish(Integer.decode("0x046E"),"Luxembourgish","Luxembourg"),
    Macao_SAR(Integer.decode("0x1404"),"Chinese","Macao S.A.R."),
    Malaysia_English(Integer.decode("0x4409"),"English","Malaysia"),
    Malaysia_Malay(Integer.decode("0x043E"),"Malay","Malaysia"),
    Maldives(Integer.decode("0x0465"),"Divehi","Maldives"),
    Malta(Integer.decode("0x043A"),"Maltese","Malta"),
    Mexico(Integer.decode("0x080A"),"Spanish","Mexico"),
    Mohawk(Integer.decode("0x047C"),"Mohawk","Mohawk"),
    Mongolia(Integer.decode("0x0450"),"Mongolian (Cyrillic)","Mongolia"),
    Morocco(Integer.decode("0x1801"),"Arabic","Morocco"),
    Nepal(Integer.decode("0x0461"),"Nepali","Nepal"),
    Netherlands_Dutch(Integer.decode("0x0413"),"Dutch","Netherlands"),
    Netherlands_Frisian(Integer.decode("0x0462"),"Frisian","Netherlands"),
    New_Zealand_English(Integer.decode("0x1409"),"English","New Zealand"),
    New_Zealand_Maori(Integer.decode("0x0481"),"Maori","New Zealand"),
    Nicaragua(Integer.decode("0x4C0A"),"Spanish","Nicaragua"),
    Nigeria_Hausa(Integer.decode("0x0468"),"Hausa (Latin)","Nigeria"),
    Nigeria_Igbo(Integer.decode("0x0470"),"Igbo","Nigeria"),
    Nigeria_Yoruba(Integer.decode("0x046A"),"Yoruba","Nigeria"),
    North_Macedonia(Integer.decode("0x042F"),"Macedonian","North Macedonia"),
    Norway_Bokmal(Integer.decode("0x0414"),"Norwegian (Bokmal)","Norway"),
    Norway_Lule(Integer.decode("0x103B"),"Sami (Lule)","Norway"),
    Norway_Northern(Integer.decode("0x043B"),"Sami (Northern)","Norway"),
    Norway_Nynorsk(Integer.decode("0x0814"),"Norwegian (Nynorsk)","Norway"),
    Norway_Southern(Integer.decode("0x183B"),"Sami (Southern)","Norway"),
    Oman(Integer.decode("0x2001"),"Arabic","Oman"),
    Panama(Integer.decode("0x180A"),"Spanish","Panama"),
    Paraguay(Integer.decode("0x3C0A"),"Spanish","Paraguay"),
    Peoples_Republic_of_China_Chinese(Integer.decode("0x0804"),"Chinese","People’s Republic of China"),
    Peoples_Republic_of_China_Mongolian(Integer.decode("0x0850"),"Mongolian (Traditional)","People’s Republic of China"),
    Peru_Quechua(Integer.decode("0x0C6B"),"Quechua","Peru"),
    Peru(Integer.decode("0x280A"),"Spanish","Peru"),
    Philippines(Integer.decode("0x0464"),"Filipino","Philippines"),
    Poland(Integer.decode("0x0415"),"Polish","Poland"),
    Portugal(Integer.decode("0x0816"),"Portuguese","Portugal"),
    PRC_Yi(Integer.decode("0x0478"),"Yi","PRC"),
    PRC_Tibetan(Integer.decode("0x0451"),"Tibetan","PRC"),
    PRC_Uighur(Integer.decode("0x0480"),"Uighur","PRC"),
    Principality_of_Monaco(Integer.decode("0x180C"),"French","Principality of Monaco"),
    Puerto_Rico(Integer.decode("0x500A"),"Spanish","Puerto Rico"),
    Qatar(Integer.decode("0x4001"),"Arabic","Qatar"),
    Republic_of_the_Philippines(Integer.decode("0x3409"),"English","Republic of the Philippines"),
    Romania(Integer.decode("0x0418"),"Romanian","Romania"),
    Russia_Bashkir(Integer.decode("0x046D"),"Bashkir","Russia"),
    Russia_Russian(Integer.decode("0x0419"),"Russian","Russia"),
    Russia_Tatar(Integer.decode("0x0444"),"Tatar","Russia"),
    Russia_Yakut(Integer.decode("0x0485"),"Yakut","Russia"),
    Rwanda(Integer.decode("0x0487"),"Kinyarwanda","Rwanda"),
    Saudi_Arabia(Integer.decode("0x0401"),"Arabic","Saudi Arabia"),
    Senegal(Integer.decode("0x0488"),"Wolof","Senegal"),
    Serbia_Cyrillic(Integer.decode("0x0C1A"),"Serbian (Cyrillic)","Serbia"),
    Serbia_Latin(Integer.decode("0x081A"),"Serbian (Latin)","Serbia"),
    Singapore_Chinese(Integer.decode("0x1004"),"Chinese","Singapore"),
    Singapore_English(Integer.decode("0x4809"),"English","Singapore"),
    Slovakia_Slovak(Integer.decode("0x041B"),"Slovak","Slovakia"),
    Slovenia_Slovenian(Integer.decode("0x0424"),"Slovenian","Slovenia"),
    South_Africa_Afrikaans(Integer.decode("0x0436"),"Afrikaans","South Africa"),
    South_Africa_English(Integer.decode("0x1C09"),"English","South Africa"),
    South_Africa_Sesotho(Integer.decode("0x046C"),"Sesotho sa Leboa","South Africa"),
    South_Africa_Setswana(Integer.decode("0x0432"),"Setswana","South Africa"),
    South_Africa_Xhosa(Integer.decode("0x0434"),"isiXhosa","South Africa"),
    South_Africa_Zulu(Integer.decode("0x0435"),"isiZulu","South Africa"),
    Spain_Modern(Integer.decode("0x0C0A"),"Spanish (Modern Sort)","Spain"),
    Spain_Tradition(Integer.decode("0x040A"),"Spanish (Traditional Sort)","Spain"),
    Sri_Lanka(Integer.decode("0x045B"),"Sinhala","Sri Lanka"),
    Sweden_Swedish(Integer.decode("0x041D"),"Swedish","Sweden"),
    Sweden_Lule(Integer.decode("0x143B"),"Sami (Lule)","Sweden"),
    Sweden_Northern(Integer.decode("0x083B"),"Sami (Northern)","Sweden"),
    Sweden_Southern(Integer.decode("0x1C3B"),"Sami (Southern)","Sweden"),
    Switzerland_French(Integer.decode("0x100C"),"French","Switzerland"),
    Switzerland_German(Integer.decode("0x0807"),"German","Switzerland"),
    Switzerland_Italian(Integer.decode("0x0810"),"Italian","Switzerland"),
    Switzerland_Romansh(Integer.decode("0x0417"),"Romansh","Switzerland"),
    Syria_Arabic(Integer.decode("0x2801"),"Arabic","Syria"),
    Syria_Syriac(Integer.decode("0x045A"),"Syriac","Syria"),
    Taiwan(Integer.decode("0x0404"),"Chinese","Taiwan"),
    Tajikistan(Integer.decode("0x0428"),"Tajik (Cyrillic)","Tajikistan"),
    Thailand_Thai(Integer.decode("0x041E"),"Thai","Thailand"),
    Trinidad_and_Tobago_English(Integer.decode("0x2C09"),"English","Trinidad and Tobago"),
    Tunisia(Integer.decode("0x1C01"),"Arabic","Tunisia"),
    Turkey(Integer.decode("0x041F"),"Turkish","Turkey"),
    Turkmenistan(Integer.decode("0x0442"),"Turkmen","Turkmenistan"),
    UAE(Integer.decode("0x3801"),"Arabic","U.A.E."),
    Ukraine(Integer.decode("0x0422"),"Ukrainian","Ukraine"),
    United_Kingdom_English(Integer.decode("0x0809"),"English","United Kingdom"),
    United_Kingdom_Welsh(Integer.decode("0x0452"),"Welsh","United Kingdom"),
    United_States_English(Integer.decode("0x0409"),"English","United States"),
    United_States_Spanish(Integer.decode("0x540A"),"Spanish","United States"),
    Uruguay(Integer.decode("0x380A"),"Spanish","Uruguay"),
    Uzbekistan_Cyrillic(Integer.decode("0x0843"),"Uzbek (Cyrillic)","Uzbekistan"),
    Uzbekistan_Latin(Integer.decode("0x0443"),"Uzbek (Latin)","Uzbekistan"),
    Venezuela(Integer.decode("0x200A"),"Spanish","Venezuela"),
    Vietnam(Integer.decode("0x042A"),"Vietnamese","Vietnam"),
    Yemen(Integer.decode("0x2401"),"Arabic","Yemen"),
    Zimbabwe(Integer.decode("0x3009"),"English","Zimbabwe");


    private final int code; 
    private final String language;
    private final String region;
    
    private LanguageIDsWindowsEnum(int code, String language, String region)
    {
        this.code = code;
        this.language = language;
        this.region = region;
    }
    
    //store the enums in the following map
    private static final Map<Integer, LanguageIDsWindowsEnum> BY_CODE_MAP = new LinkedHashMap<>();
    //automatic call to store enums in map
    static {
        for (LanguageIDsWindowsEnum LanguageIDsEnum : LanguageIDsWindowsEnum.values()) {
            BY_CODE_MAP.put(LanguageIDsEnum.code, LanguageIDsEnum);
        }
    }

    //scour through the available platform based on code
    public static LanguageIDsWindowsEnum forCode(int code) {
        return BY_CODE_MAP.get(code);
    }
    
    @Override
    public String toString()
    {
        return language+ ", " +region;
    }
}
