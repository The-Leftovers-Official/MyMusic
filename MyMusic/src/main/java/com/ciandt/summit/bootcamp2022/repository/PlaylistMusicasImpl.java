package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.music.Music;
import com.ciandt.summit.bootcamp2022.entity.playlist.Playlist;
import com.ciandt.summit.bootcamp2022.entity.playlist.PlaylistMusics;
import com.ciandt.summit.bootcamp2022.entity.playlist.PlaylistMusicsRepository;
import com.ciandt.summit.bootcamp2022.infra.entity.music.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicsPKEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
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



    @Override
    public List<PlaylistMusicas> findByPlaylistId(String playlistId) {
        List<PlaylistMusicas> playlistMusicas = playlistMusicsWithJpa.findByPlaylistId(playlistId);
        if(!playlistMusicas.isEmpty()){
            return playlistMusicas;
        }

        throw new IllegalArgumentException("Playlist not found!");
    }
    }
