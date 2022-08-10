package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.infra.entity.artist.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepositoryWithJpa extends JpaRepository<ArtistEntity, String> {

}
