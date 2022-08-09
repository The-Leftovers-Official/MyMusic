package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.PlaylistMusics;
import com.ciandt.summit.bootcamp2022.entity.PlaylistMusicsRepository;
import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicsPKEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PlaylistMusicasImpl implements PlaylistMusicsRepository {

    private final PlaylistMusicsWithJpa playlistMusicsWithJpa;

    private final ModelMapper modelMapper;

    @Override
    public void deleteMusicFromPlaylist(String playlistId, String musicID) {

    }

    @Override
    public PlaylistMusics savePlaylist(Music music, Playlist playlist) {

        PlaylistMusicsPKEntity playlistMusicsPKEntity = PlaylistMusicsPKEntity.builder()
                .musicId(music.getId())
                .playlistId(playlist.getId())
                .build();

        PlaylistMusicas playlistMusicas = PlaylistMusicas
                .builder()
                .id(playlistMusicsPKEntity)
                .playlist(new PlaylistEntity(playlist))
                .music(new MusicEntity(music))
                .build();

        PlaylistMusicas returnedPlaylistMusic = playlistMusicsWithJpa.save(playlistMusicas);
        return modelMapper.map(returnedPlaylistMusic, PlaylistMusics.class);
    }
}
