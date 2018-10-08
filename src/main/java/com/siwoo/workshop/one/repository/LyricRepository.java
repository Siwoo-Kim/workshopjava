package com.siwoo.workshop.one.repository;

import com.siwoo.workshop.one.model.Lyric;

import java.io.File;
import java.util.Optional;


/**
 * Created by Siwoo Kim on 2018-10-08
 */

public interface LyricRepository {

    void fetchLyrics(File file);

    boolean contains(Lyric lyric);

    Lyric save(Lyric lyric);

    Lyric merge(Lyric lyric);

    void print();

    Optional<Lyric> findByDay(String day);

    Lyric findBySequence(Lyric.Sequence sequence);
}
