package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.MusicRepository;
import com.ciandt.summit.bootcamp2022.entity.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlaylistRepositoryImpl implements PlaylistRepository {

    private final PlaylistRepositoryWithJpa playlistRepositoryWithJpa;
    private final MusicRepositoryImpl musicRepository;

  @Override
  public List<Music> addMusics(String playlistId, List<Music> musicList) {
    Optional<PlaylistEntity> playlistEntity = findPlaylist(playlistId);
    List<MusicEntity> list = new ArrayList<>();

    for (Music music: musicList) {
      MusicEntity musicEntity = new MusicEntity(musicRepository.findMusic(music.getId()));
      list.add(musicEntity);
    }

    for (MusicEntity musicEntity: list) {
      playlistEntity.get().addMusics(musicEntity);
      log.info(musicEntity.getArtist().getName() + " - " + musicEntity.getName() + " added successfully!");
    }
    return musicList;
  }

  @Override
  public Music addMusic(String playlistId, Music music) {
    Optional<PlaylistEntity> playlistEntity = findPlaylist(playlistId);

    MusicEntity musicEntity = new MusicEntity(musicRepository.findMusic(music.getId()));

    playlistEntity.get().addMusics(musicEntity);
    log.info(musicEntity.getArtist().getName() + " - " + musicEntity.getName() + " added successfully!");

    return music;
  }

  private Optional<PlaylistEntity> findPlaylist(String playlistId) {
    Optional<PlaylistEntity> playlistEntity = playlistRepositoryWithJpa.findById(playlistId);
    if(!playlistEntity.isPresent()){
      throw new IllegalArgumentException("Playlist not found!");
    }
    return playlistEntity;
  }
}
