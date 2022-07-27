package com.ciandt.summit.bootcamp2022.repository;

import java.util.List;

import com.ciandt.summit.bootcamp2022.model.MusicEntity;

public interface MusicRepository {
    void saveMusic(MusicEntity music);

    List<MusicEntity> getAllMusics();

    List<MusicEntity> getMusicsByArtistName(String artistName);

    List<MusicEntity> getMusicsByArtistNameAndMusicName(String artistId);

}
