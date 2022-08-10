package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.artist.Artist;
import com.ciandt.summit.bootcamp2022.entity.music.Music;
import com.ciandt.summit.bootcamp2022.entity.playlist.Playlist;
import com.ciandt.summit.bootcamp2022.entity.playlist.PlaylistMusics;
import com.ciandt.summit.bootcamp2022.entity.playlist.PlaylistMusicsPK;
import com.ciandt.summit.bootcamp2022.infra.entity.artist.ArtistEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.music.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicsPKEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PlaylistMusicasImplTest {

    @Mock
    private PlaylistMusicsWithJpa playlistMusicsWithJpa;

    @InjectMocks
    private PlaylistMusicasImpl playlistMusicas;

    @Mock
    private ModelMapper modelMapper;
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

    @Test
    public void shouldSavePlaylist() {

        PlaylistMusicas playlistMusics = createPlaylistMusicasDbEntityObj();

        PlaylistMusics expected = createPlayListMusic();

        Artist artist = Artist.builder()
                .id("1")
                .name("artist")
                .build();

        Music music = Music.builder()
                .id("1")
                .name("music")
                .artist(artist)
                .playlists(new ArrayList<>()).build();

        Playlist playlist = Playlist.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();

        Mockito.when(playlistMusicsWithJpa.save(Mockito.any())).thenReturn(playlistMusics);

        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(expected);

        PlaylistMusics returnedPlaylist = playlistMusicas.savePlaylist(music, playlist);

        Assertions.assertEquals(expected ,returnedPlaylist);

    }

    private PlaylistMusics createPlayListMusic() {
        Artist artist = Artist.builder()
                .id("1")
                .name("artist")
                .build();

        Music music = Music.builder()
                .id("1")
                .name("music")
                .artist(artist)
                .playlists(new ArrayList<>()).build();

        Playlist playlist = Playlist.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();

        PlaylistMusicsPK playlistMusicsPK = PlaylistMusicsPK.builder()
                .musicId(music.getId())
                .playlistId(playlist.getId())
                .build();

        PlaylistMusics expected = PlaylistMusics
                .builder()
                .id(playlistMusicsPK)
                .playlist(playlist)
                .music(music)
                .build();
        return expected;

    }

    private PlaylistMusicas createPlaylistMusicasDbEntityObj(){
        Artist artist = Artist.builder()
                .id("1")
                .name("artist")
                .build();

        Music music = Music.builder()
                .id("1")
                .name("music")
                .artist(artist)
                .playlists(new ArrayList<>()).build();

        Playlist playlist = Playlist.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();

        PlaylistMusicsPKEntity playlistMusicsPKEntity = PlaylistMusicsPKEntity.builder()
                .musicId(music.getId())
                .playlistId(playlist.getId())
                .build();

        PlaylistMusicas playlistMusics = PlaylistMusicas
                .builder()
                .id(playlistMusicsPKEntity)
                .playlist(new PlaylistEntity(playlist))
                .music(new MusicEntity(music))
                .build();

        return playlistMusics;
    }
}