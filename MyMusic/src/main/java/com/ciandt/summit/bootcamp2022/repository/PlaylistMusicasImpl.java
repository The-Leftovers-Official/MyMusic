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
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j
public class PlaylistMusicasImpl implements PlaylistMusicsRepository {

    private final PlaylistMusicsWithJpa playlistMusicsWithJpa;

    private final ModelMapper modelMapper;

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

    @Override
    public void deleteMusicFromPlaylist(String playlistId, String musicId) {
        PlaylistMusicsPKEntity pk = PlaylistMusicsPKEntity
                .builder()
                .musicId(musicId)
                .playlistId(playlistId)
                .build();

        Optional<PlaylistMusicas> playlistMusicas = playlistMusicsWithJpa.findById(pk);

        if (playlistMusicas.isPresent()){
            playlistMusicsWithJpa.deleteMusicFromPlaylist(playlistId, musicId);

        } else{
            throw new IllegalArgumentException("Music or playlist not found!");
        }

    }
}
