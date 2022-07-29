package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepositoryWithJpa extends PagingAndSortingRepository<MusicEntity, String>, JpaRepository<MusicEntity, String> {

    Page<MusicEntity> findByNameContainingIgnoreCaseOrArtistNameContainingIgnoreCaseOrderByArtistNameAscName(String name, String musicName, Pageable pageable);

}
