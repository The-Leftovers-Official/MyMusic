package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import com.ciandt.summit.dto.MusicDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepositoryWithJpa extends MusicRepository, JpaRepository<MusicEntity, String> {

    @Override
    default void saveMusic(MusicEntity music) {
        this.save(music);
    }

    @Query("SELECT m, a FROM MusicEntity m, ArtistEntity a WHERE lower(m.name) = ?1 AND lower(a.name) = ?1")
    Page<MusicDto> findMusicByFilter(Pageable pageable, String musicOrArtistName);

    @Query("SELECT m FROM MusicEntity m")
    Page<MusicEntity> getAllMusics(Pageable pageable);

    @Query("SELECT m FROM MusicEntity m WHERE lower(m.artist.name) = :artistName")
    List<MusicEntity> getMusicsByArtistName(String artistName);

}
