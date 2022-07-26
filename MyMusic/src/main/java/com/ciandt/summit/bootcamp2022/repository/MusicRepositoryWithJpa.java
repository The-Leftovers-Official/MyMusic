package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.model.ArtistEntity;
import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepositoryWithJpa extends JpaRepository<MusicEntity, String> {

    @Query("SELECT m, a FROM MusicEntity m, ArtistEntity a WHERE lower(m.name) = ?1 AND lower(a.name) = ?1")
    Page<ArtistEntity> findMusicByFilter(String musicOrArtistName);



}
