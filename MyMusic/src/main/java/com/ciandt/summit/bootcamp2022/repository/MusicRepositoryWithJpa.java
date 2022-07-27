package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.model.ArtistEntity;
import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepositoryWithJpa extends JpaRepository<MusicEntity, String> {

    //@Query("SELECT m, a FROM MusicEntity m, ArtistEntity a WHERE lower(m.name) = :name OR lower(a.name) = :name")
    //@Query(value = "SELECT m.Nome, a.Nome FROM Musicas m JOIN Artistas a WHERE m.Nome = :name OR a.Nome = :name", nativeQuery = true)
    //@Query("SELECT m FROM MusicEntity m WHERE m.name = :name")
    List<MusicEntity> findByArtistNameContainingIgnoreCaseOrderByArtistNameAsc(String name);

}
