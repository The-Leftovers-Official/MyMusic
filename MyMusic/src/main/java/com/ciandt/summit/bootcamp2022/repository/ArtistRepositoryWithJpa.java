package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.model.ArtistEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepositoryWithJpa extends ArtistRepository, JpaRepository<ArtistEntity, String> {
    @Override
    default void saveArtist(ArtistEntity artist) {
        this.save(artist);
    }

    @Query("SELECT a FROM ArtistEntity a")
    List<ArtistEntity> getAllArtists();

    @Query("SELECT a FROM ArtistEntity a WHERE lower(a.name) = ?1")
    List<ArtistEntity> getArtistsByArtistName(String artistName);

}
