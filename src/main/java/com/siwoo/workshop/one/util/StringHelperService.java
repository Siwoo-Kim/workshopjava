package com.siwoo.workshop.one.util;

import java.util.Arrays;
import java.util.function.Predicate;


/**
 * Created by Siwoo Kim on 2018-10-08
 */

public interface StringHelperService {

    enum WordType {
        DIGIT((c) -> Character.isDigit(c)),
        MATH_SYMBOL(c -> {
            int type = Character.getType(c);
            return type == Character.MATH_SYMBOL;
        }),
        WORD(c -> Character.isAlphabetic(c)),
        VOWEL(c -> {
            Character[] vowels = {'a', 'e', 'i', 'o', 'u'};
            Character _c = Character.toLowerCase(c);
            return Arrays.asList(vowels).contains(_c);
        }),
        ALLWORD(c -> !Character.isWhitespace(c));

        Predicate<Character> operator;

        WordType(Predicate<Character> operator) {
            this.operator = operator;
        }
    }

    int count(String exp);

    int count(String exp, StringHelperServiceImpl.WordType type);
}
