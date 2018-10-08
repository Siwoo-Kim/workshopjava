package com.siwoo.workshop.one.util;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by sm123tt@gmail.com on 2018-10-08
 * Project : workshop1
 * Github : http://github.com/Siwoo-Kim
 */

public class StringHelperServiceImpl implements StringHelperService {

    @Override
    public int count(String exp) {
        return count(exp, WordType.WORD);
    }

    @Override
    public int count(String exp, WordType type) {
        return doCount(exp, type.operator);
    }

    private int doCount(String exp, Predicate matcher) {
        if(exp == null || exp.isEmpty()) return 0;
        int count = 0;
        for(int start = 0; start < exp.length(); start++) {
            char c = exp.charAt(start);
            if(matcher.test(c)) {
                count++;
            }
        }
        return count;
    }
}
