/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.util.Arrays;

/**
 *
 * @author jmburu
 */
public class Test {
    public static void main(String... args)
    {
        String  values[] = {".notdef", "delete", "restore_from_trash", "delete_forever"};
        boolean contains = Arrays.asList(values).contains(".notdef");
        System.out.println(contains);
    }
}
