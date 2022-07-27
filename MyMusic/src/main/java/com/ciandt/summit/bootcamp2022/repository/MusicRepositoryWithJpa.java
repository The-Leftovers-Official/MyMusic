package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.model.ArtistEntity;
import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepositoryWithJpa extends MusicRepository, JpaRepository<MusicEntity, String> {

    @Query("SELECT m, a FROM MusicEntity m, ArtistEntity a WHERE lower(m.name) = :musicOrArtistName AND lower(a.name) = :musicOrArtistName")
    List<ArtistEntity> findMusicByFilter(@Param("name") String musicOrArtistName);

    @Query("SELECT m FROM MusicEntity m")
    List<MusicEntity> getAllMusics();

    @Query("SELECT m FROM MusicEntity m WHERE lower(m.artist.name) = :artistName")
    List<MusicEntity> getMusicsByArtistName(String artistName);

    @Query("SELECT m FROM MusicEntity m WHERE lower(m.artist.name) = :artistName AND lower(m.name) = :musicName")
    List<MusicEntity> getMusicsByArtistNameAndMusicName(String artistName, String musicName);

}
