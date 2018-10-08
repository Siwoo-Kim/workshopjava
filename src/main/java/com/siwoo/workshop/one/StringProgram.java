package com.siwoo.workshop.one;

import com.siwoo.workshop.one.util.StringHelperService;
import com.siwoo.workshop.one.util.StringHelperServiceImpl;

/**
 * Created by Siwoo Kim on 2018-10-08
 */

public class StringProgram {

    public static StringHelperService stringHelperService;

    static {
         stringHelperService = new StringHelperServiceImpl();
    }

    public static void main(String[] args) {
        int count = stringHelperService.count("abc123tgsdfds dsf \t\tfdsf", StringHelperService.WordType.ALLWORD);
        if(count != 20) {
            System.exit(-1);
        } else {
            System.out.println("Word count : " + count);
        }

        count = stringHelperService.count("abc123teegsdfds UUdd dsfoo \t\tfdsf", StringHelperService.WordType.VOWEL);
        if(count != 7) {
            System.exit(-1);
        } else {
            System.out.println("Vowel count : " + count);
        }
    }
}
