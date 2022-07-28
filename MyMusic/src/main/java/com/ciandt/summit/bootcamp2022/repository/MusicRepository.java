package com.ciandt.summit.bootcamp2022.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import com.ciandt.summit.dto.MusicDto;

public interface MusicRepository {
    void saveMusic(MusicEntity music);

    Page<MusicEntity> getAllMusics(Pageable pageable);

    Page<MusicDto> findMusicByFilter(Pageable pageable, String musicOrArtistName);

    List<MusicEntity> getMusicsByArtistName(String artistName);

}
