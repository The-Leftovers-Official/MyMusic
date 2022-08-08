package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.infra.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PlaylistRepositoryImplTest {
    @Mock
    PlaylistRepositoryWithJpa playlistRepositoryWithJpa;
    @Mock
    MusicRepositoryImpl musicRepository;

    @Mock
    PlaylistMusicsWithJpa playlistMusicsWithJpa;


    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    PlaylistRepositoryImpl playlistRepository;

    @Mock
    private PlaylistMusicasImpl playlistMusicas;

    @Test
    public void shouldAddMusic (){

        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();

        Mockito.when(playlistRepositoryWithJpa.findById(Mockito.any())).thenReturn(Optional.of(playlistEntity));

        Artist artist = Artist.builder().id("1").name("artist").build();
        Music music = Music.builder().id("1").name("music1").artist(artist).playlists(new ArrayList<>()).build();

        Mockito.when(musicRepository.findMusic(Mockito.any())).thenReturn(music);

        Playlist playlist = Playlist.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();


        PlaylistMusicsPK pk = PlaylistMusicsPK.builder().musicId(music.getId()).playlistId(playlistEntity.getId()).build();
        PlaylistMusics playlistMusics = PlaylistMusics.builder()
                .id(pk)
                .music(music)
                .playlist(playlist)
                .build();

        Mockito.when(playlistMusicas.savePlaylist(Mockito.any(), Mockito.any())).thenReturn(playlistMusics);

        Music returnedMusic = playlistRepository.addMusic("1", music);

        Assertions.assertEquals(music, returnedMusic);
    }

    @Test
    public void shouldAddMusics (){
        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();

        Mockito.when(playlistRepositoryWithJpa.findById(Mockito.any())).thenReturn(Optional.of(playlistEntity));

        Artist artist = Artist.builder()
                .id("1")
                .name("artist")
                .build();

        Music music1 = Music.builder()
                .id("1")
                .name("music1")
                .artist(artist)
                .playlists(new ArrayList<>()).build();

        Music music2 = Music.builder()
                .id("2")
                .name("music2")
                .artist(artist)
                .playlists(new ArrayList<>()).build();

        Mockito.when(musicRepository.findMusic(Mockito.any())).thenReturn(music1);
        Mockito.when(musicRepository.findMusic(Mockito.any())).thenReturn(music2);

        Playlist playlist = Playlist.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();

        PlaylistMusicsPK pk1 = PlaylistMusicsPK.builder()
                .musicId(music1.getId())
                .playlistId(playlistEntity.getId())
                .build();

        PlaylistMusicsPK pk2 = PlaylistMusicsPK.builder()
                .musicId(music2.getId())
                .playlistId(playlistEntity.getId())
                .build();

        PlaylistMusics playlistMusics1 = PlaylistMusics.builder()
                .id(pk1)
                .music(music1)
                .playlist(playlist)
                .build();

        PlaylistMusics playlistMusics2 = PlaylistMusics.builder()
                .id(pk2)
                .music(music2)
                .playlist(playlist)
                .build();

        Mockito.when(playlistMusicas.savePlaylist(Mockito.any(), Mockito.any())).thenReturn(playlistMusics1);
        Mockito.when(playlistMusicas.savePlaylist(Mockito.any(), Mockito.any())).thenReturn(playlistMusics2);

        List<Music> musicList = playlistRepository.addMusics(playlist.getId(), new ArrayList<>(Arrays.asList(music1, music2)));

        Assertions.assertEquals(new ArrayList<>(Arrays.asList(music1, music2)), musicList);

    }

    @Test
    public void shouldReturnIllegalArgumentException (){

        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .id("1")
                .musics(new ArrayList<>())
                .build();

        Mockito.when(playlistRepositoryWithJpa.findById(Mockito.any())).thenThrow(new IllegalArgumentException());

        try{
            playlistRepository.addMusic("1", new Music());
        } catch (Exception e){
            Assertions.assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }
}