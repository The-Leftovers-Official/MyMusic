package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaylistRepositoryImpl implements PlaylistRepository {

    private final PlaylistRepositoryWithJpa playlist;

//    @Override
//    public PlaylistEntity addMusic(MusicEntity music) {
//        PlaylistEntity playlistEntity = playlist.addMusic(music.);
//
//        return playlistEntity;
//    }

  @Override
  public Playlist addMusic(Music music) {
    MusicEntity musicEntity = new MusicEntity(music);
    //playlist.save(musicEntity);
    return null;
  }
}
