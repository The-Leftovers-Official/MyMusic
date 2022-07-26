package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.model.ArtistEntity;
import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepositoryWithJpa extends JpaRepository<MusicEntity, String> {

    @Query("SELECT m, a FROM MusicEntity m, ArtistEntity a WHERE lower(m.name) = :musicOrArtistName AND lower(a.name) = :musicOrArtistName")
    Page<ArtistEntity> findMusicByFilter(@Param("name") String musicOrArtistName);

}
