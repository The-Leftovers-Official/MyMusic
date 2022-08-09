package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.infra.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PlaylistMusicasImplTest {

    @Mock
    private PlaylistMusicsWithJpa playlistMusicsWithJpa;

    @InjectMocks
    private PlaylistMusicasImpl playlistMusicas;

    @Test
    public void shouldDeleteMusicFromPlaylist() {

        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();

        ArtistEntity artist = ArtistEntity.builder()
                .id("1")
                .name("artist")
                .build();

        MusicEntity music = MusicEntity.builder()
                .id("1")
                .name("music")
                .artist(artist)
                .playlists(new ArrayList<>()).build();

        PlaylistMusicsPKEntity pk = PlaylistMusicsPKEntity.builder()
                .musicId(music.getId())
                .playlistId(playlistEntity.getId())
                .build();


        PlaylistMusicas playlist = PlaylistMusicas
                .builder().id(pk).playlist(playlistEntity).music(music).build();

        Mockito.when(playlistMusicsWithJpa.findById(Mockito.any())).thenReturn(Optional.of(playlist));

        try{
            playlistMusicas.deleteMusicFromPlaylist("1", "1");
        }catch (IllegalArgumentException e) {
            Assertions.fail();
        }

    }

    @Test
    public void shouldThrowNewExceptionWhenMusicOrPlaylistNotFound() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {playlistMusicas.deleteMusicFromPlaylist("1", "1");});

    }

}