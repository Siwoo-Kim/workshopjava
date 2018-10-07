package com.siwoo.workshop.one.model;

import com.siwoo.workshop.one.SongProgram;

import java.util.Objects;

/**
 * Created by sm123tt@gmail.com on 2018-10-07
 * Project : workshop1
 * Github : http://github.com/Siwoo-Kim
 */

public class Lyric implements Comparable<Lyric>{
    private final String day;
    private final Sequence select;
    private String verse;

    public enum Sequence {
        TWELFTH(12, null),
        ELEVENTH(11, TWELFTH),
        TENTH(10, ELEVENTH),
        NINTH(9, TENTH),
        EIGHTH(8, NINTH),
        SEVENTH(7, EIGHTH),
        SIXTH(6, SEVENTH),
        FIFTH(5, SIXTH),
        FOURTH(4, SEVENTH),
        THIRD(3, FOURTH),
        SECOND(2, THIRD),
        FIRST(1, SECOND);

        final Sequence nextSequence;

        final int intValue;
        Sequence(int level, Sequence nextSequence) {
            this.intValue = level;
            this.nextSequence = nextSequence;
        }

        public static Sequence valueOf(int level) {
            for(Sequence sequence: Sequence.values()) {
                if(level == sequence.intValue) {
                    return sequence;
                }
            }
            throw new IllegalArgumentException("unknown value: " + level);
        }

        public int getValue() {
            return intValue;
        }

        public String getDay() {
            return toString() + " DAY";
        }
    }
    public String getDay() {
        return day;
    }

    public Sequence getSelect() {
        return select;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public Lyric(String day, String verse) {
        try {
            this.day = day.toUpperCase();
            this.select = Sequence.valueOf(day.split(" ")[0].toUpperCase());
            this.verse = verse;
        }catch (Exception e) {
            throw new IllegalArgumentException("unknown day : " + day);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lyric lyric = (Lyric) o;
        return Objects.equals(day, lyric.day) &&
                select == lyric.select;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }

    @Override
    public int compareTo(Lyric o) {
        return day.compareToIgnoreCase(o.day);
    }
//
//    @Override
//    public String toString() {
//        return "Lyric{" +
//                "day='" + day + '\'' +
//                ", select=" + select +
//                '}';
//    }
    @Override
    public String toString() {
        return "Lyric{" +
                "day='" + day + '\'' +
                ", verse='" + verse + '\'' +
                '}';
    }
}
