package com.ciandt.summit.bootcamp2022.repository;

import java.util.List;

import com.ciandt.summit.bootcamp2022.model.ArtistEntity;

public interface ArtistRepository {
    void saveArtist(ArtistEntity artist);

    List<ArtistEntity> getAllArtists();

    List<ArtistEntity> getArtistsByArtistName(String artistName);

}
