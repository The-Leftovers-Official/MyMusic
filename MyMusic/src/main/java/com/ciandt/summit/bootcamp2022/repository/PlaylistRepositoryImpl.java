package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlaylistRepositoryImpl implements PlaylistRepository {

    private final PlaylistRepositoryWithJpa playlistRepositoryWithJpa;
    private final MusicRepositoryWithJpa musicRepositoryWithJpa;

  @Override
  public List<Music> addMusics(String playlistId, List<Music> convertIntoListMusic) {
    return null;
  }

  @Override
  public Music addMusic(String playlistId, Music music) {
    Optional<PlaylistEntity> playlistEntity = playlistRepositoryWithJpa.findById(playlistId);
    if(!playlistEntity.isPresent()){
      throw new IllegalArgumentException("Playlist not found!");
    }

    Optional<MusicEntity> musicEntity = musicRepositoryWithJpa.findById(music.getId());
    if(!musicEntity.isPresent()){
      throw new IllegalArgumentException("Music not found!");
    }

    MusicEntity musicEntity1 = new MusicEntity(music);
    playlistEntity.get().addMusics(musicEntity1);
    log.info(musicEntity1.getArtist().getName() + " - " + musicEntity1.getName() + " added successfully!");

    return music;
  }
}
