package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.PlaylistMusics;
import com.ciandt.summit.bootcamp2022.entity.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlaylistRepositoryImpl implements PlaylistRepository {

    private  final PlaylistRepositoryWithJpa playlistRepositoryWithJpa;

    private  final MusicRepositoryImpl musicRepository;

    private  final PlaylistMusicasImpl playlistMusicsImpl;
    private final ModelMapper modelMapper;

  @Override
  public List<Music> addMusics(String playlistId, List<Music> musicList) {
    Optional<PlaylistEntity> playlistEntity = findPlaylist(playlistId);
    Playlist playlist = modelMapper.map(playlistEntity.get(), Playlist.class);
    List<MusicEntity> list = new ArrayList<>();
    PlaylistMusics playlistMusicas;

    for (Music music: musicList) {
      MusicEntity musicEntity = new MusicEntity(musicRepository.findMusic(music.getId()));
      list.add(musicEntity);
    }

    for (MusicEntity musicEntity: list) {
      playlistEntity.get().addMusics(musicEntity);
      playlistMusicas = playlistMusicsImpl.savePlaylist(modelMapper.map(musicEntity, Music.class), playlist);
      log.info("Playlist id: " + playlistMusicas.getPlaylist().getId() + " - " + playlistMusicas.getMusic().getName());
      log.info(musicEntity.getArtist().getName() + " - " + musicEntity.getName() + " added successfully!");
    }
    return musicList;
  }

  @Override
  public Music addMusic(String playlistId, Music music) {
    Optional<PlaylistEntity> playlistEntity = findPlaylist(playlistId);

    MusicEntity musicEntity = new MusicEntity(musicRepository.findMusic(music.getId()));

    playlistEntity.get().addMusics(musicEntity);

    Music music1 = modelMapper.map(musicEntity, Music.class);
    Playlist playlist = modelMapper.map(playlistEntity.get(), Playlist.class);
    PlaylistMusics returnedPlaylist = playlistMusicsImpl.savePlaylist(music1, playlist);

    log.info("Playlist id: " + returnedPlaylist.getPlaylist().getId() + " - " + returnedPlaylist.getMusic().getName());
    log.info(musicEntity.getArtist().getName() + " - " + musicEntity.getName() + " added successfully!");


    return music;
  }

  @Override
  public void deleteMusic(String playlistId, Music music) {
    Optional<PlaylistEntity> playlistEntity = findPlaylist(playlistId);

    MusicEntity musicEntity = new MusicEntity(musicRepository.findMusic(music.getId()));

    Music music1 = modelMapper.map(musicEntity, Music.class);
    Playlist playlist = modelMapper.map(playlistEntity.get(), Playlist.class);
    playlistMusicsImpl.deleteMusicFromPlaylist(playlist.getId(), music1.getId());

  }

  private Optional<PlaylistEntity> findPlaylist(String playlistId) {
    Optional<PlaylistEntity> playlistEntity = playlistRepositoryWithJpa.findById(playlistId);
    if(!playlistEntity.isPresent()){
      throw new IllegalArgumentException("Playlist not found!");
    }
    return playlistEntity;
  }
}
