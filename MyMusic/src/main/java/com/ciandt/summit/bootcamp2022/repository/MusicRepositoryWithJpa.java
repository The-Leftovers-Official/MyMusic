package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepositoryWithJpa extends JpaRepository<MusicEntity, String> {

    @Query("SELECT m, a FROM MusicEntity m, ArtistEntity a WHERE lower(m.name) = :name AND lower(a.name) = :name")
    List<ArtistEntity> findMusicBy(String name);

}
