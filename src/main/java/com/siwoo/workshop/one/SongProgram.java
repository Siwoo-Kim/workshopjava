package com.siwoo.workshop.one;

import com.siwoo.workshop.one.model.Lyric;
import com.siwoo.workshop.one.repository.FileLyricRepositoryImpl;
import com.siwoo.workshop.one.repository.LyricRepository;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by sm123tt@gmail.com on 2018-10-07
 * Project : workshop1
 * Github : http://github.com/Siwoo-Kim
 */

public class SongProgram {

    public static final Path FILE = Paths.get("./src/main/java/com/siwoo/workshop/one/the12DaysOfChristmas");
    private static LyricRepository lyricRepository = new FileLyricRepositoryImpl();

    @Test
    public void test() {
        lyricRepository.fetchLyrics(FILE.toFile());
        lyricRepository.print();
    }

    static {
        lyricRepository.fetchLyrics(FILE.toFile());
    }

    public enum RESULT{
        FOUND, NOT_FOUND, ERROR, QUIT
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose below number for your lyric");
        Lyric.Sequence[] sequences = Lyric.Sequence.values();
        Arrays.sort(sequences, Comparator.comparing((Lyric.Sequence sequence) -> sequence.getValue()));

        for(Lyric.Sequence sequence: sequences) {
            System.out.printf("[%s]:%02d \n", sequence, sequence.getValue());
        }
        System.out.println();
        String input = "";

        while (true) {
            System.out.printf("Your choice >>");
            input = scanner.nextLine();
            char firstChar = input.charAt(0);
            RESULT result = null;
            Lyric lyric = null;
            Lyric.Sequence choosed = null;

            try {
                if(Character.toUpperCase(firstChar) == 'Q' && input.trim().length() == 1) {
                    result = RESULT.QUIT;
                    System.out.println(result);
                }else if (Character.isDigit(firstChar)) {
                    int intValue = Integer.parseInt(input);
                    choosed = Lyric.Sequence.valueOf(intValue);
                } else if (Character.isAlphabetic(firstChar)) {
                    choosed = Lyric.Sequence.valueOf(input.toUpperCase());
                }
            }catch (IllegalArgumentException e) {
                result = RESULT.ERROR;
            }

            if(result == null && choosed == null) {
                System.out.println("Invalid input, try again");
                result = RESULT.ERROR;
            }

            if(result == null) {
                lyric = lyricRepository.findByDay(choosed.getDay()).orElse(null);
                result = lyric == null ? RESULT.NOT_FOUND : RESULT.FOUND;
            }

            switch (result) {
                case FOUND:
                    System.out.println(lyric.getVerse());
                    break;
                case ERROR:
                    System.out.println("Invalid input!");
                    break;
                case QUIT:
                    System.out.println("Good Bye!");
                    System.exit(0);
                default:
                    System.out.println("Not found sorry!");
                    break;
            }
        }
    }

}





















