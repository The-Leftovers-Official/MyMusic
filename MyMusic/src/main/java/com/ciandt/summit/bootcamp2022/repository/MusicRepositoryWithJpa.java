package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.model.ArtistEntity;
import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepositoryWithJpa extends JpaRepository<MusicEntity, String> {


    List<MusicEntity> findByNameContainingIgnoreCaseOrArtistNameContainingIgnoreCaseOrderByArtistNameAscName(String name, String musicName);

}
