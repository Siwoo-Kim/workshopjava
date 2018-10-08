package com.siwoo.workshop.one.repository;

import com.siwoo.workshop.one.model.Lyric;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;


/**
 * Created by Siwoo Kim on 2018-10-08
 */

public class FileLyricRepositoryImpl implements LyricRepository{
    private List<Lyric> lyrics;
    private static final String LINE_DELEMITER = "On the ";
    @Override
    public void fetchLyrics(File file) {
        if(lyrics == null) {
            lyrics = new ArrayList<>();
        }
        try(Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)))) {
            scanner.useDelimiter(LINE_DELEMITER);
            while (scanner.hasNext()) {
                final String DAY_DELEMITER = " of Christ";
                final String DAY = "day";
                String input = scanner.next();
                int start = 0;
                int end = input.indexOf(DAY_DELEMITER);
                String day = "";

                try {
                    day = input.substring(start, end);
                }catch (IndexOutOfBoundsException e) {
                    end = input.indexOf(", ");
                    day = input.substring(start, end);
                    day += " day";
                }

                if(day.contains(DAY)  && (!day.endsWith(DAY) || day.split(" ").length > 2)) {
                    day = day.substring(0, day.indexOf(DAY) + DAY.length());
                }

                Lyric lyric = new Lyric(day, input);

                if(contains(lyric)) {
                    merge(lyric);
                } else {
                    save(lyric);
                }

            }

        }catch (IOException e) {
            throw new IllegalArgumentException("cannot parse the file " + file.toString());
        }
    }

    @Override
    public boolean contains(Lyric lyric) {
        return lyrics != null ? lyrics.contains(lyric) : false;
    }

    @Override
    public Lyric save(Lyric lyric) {
        if(lyrics == null) {
            lyrics = new ArrayList<>();
        }
        if(!contains(lyric)) {
            lyrics.add(lyric);
            return lyric;
        } else {
           return merge(lyric);
        }
    }

    @Override
    public Lyric merge(Lyric lyric) {
        Lyric found = findByDay(lyric.getDay()).orElse(null);
        if(found == null) {
            throw new IllegalArgumentException("not exiting lyric : "+ lyric.getDay());
        }
        found.setVerse(found.getVerse() + "\n" + lyric.getVerse());
        return found;
    }

    //Testing
    @Override
    public void print() {
        lyrics.stream().forEach(lyric ->
                System.out.printf("\tLyric Day: %02d,\nVerse: %s\n\n", lyric.getSelect().getValue(), lyric.getVerse()));
    }

    @Override
    public Optional<Lyric> findByDay(String day) {
        return lyrics.stream().filter(lyric -> lyric.getDay().equals(day)).findFirst();
//        if(day != null && !day.isEmpty()) {
//            Lyric temp = new Lyric(day, null);
//            int index = Collections.binarySearch(lyrics, temp, null);
//            if(index >= 0) {
//                return Optional.ofNullable(lyrics.get(index));
//            } else {
//                return Optional.empty();
//            }
//        }
//        return Optional.empty();
    }

    @Override
    public Lyric findBySequence(Lyric.Sequence sequence) {
        return findByDay(sequence.getDay()).orElse(null);
    }


}
