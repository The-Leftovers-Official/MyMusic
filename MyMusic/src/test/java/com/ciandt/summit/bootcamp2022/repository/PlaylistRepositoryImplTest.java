package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PlaylistRepositoryImplTest {
    @Mock
    PlaylistRepositoryWithJpa playlistRepositoryWithJpa;
    @Mock
    MusicRepositoryImpl musicRepository;

    @InjectMocks
    PlaylistRepositoryImpl playlistRepository;

    @Test
    public void shouldAddMusic (){

        Artist artist = Artist.builder().id("1").name("artist").build();
        Music music = Music.builder().id("1").name("test").artist(artist).playlists(new ArrayList<>()).build();
        PlaylistEntity playlistEntity = PlaylistEntity.builder().id("1").musics(new ArrayList<>()).build();
        Mockito.when(playlistRepositoryWithJpa.findById(Mockito.any())).thenReturn(Optional.of(playlistEntity));
        Mockito.when(musicRepository.findMusic(Mockito.any())).thenReturn(music);
        Music returnedMusic = playlistRepository.addMusic("1", music);
        Assertions.assertEquals(music, returnedMusic);
    }

    @Test
    public void shouldAddMusics (){

        Artist artist = Artist.builder().id("1").name("artist").build();
        Music music1 = Music.builder().id("1").name("music1").artist(artist).playlists(new ArrayList<>()).build();
        Music music2 = Music.builder().id("1").name("music1").artist(artist).playlists(new ArrayList<>()).build();
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);
        musicList.add(music2);
        PlaylistEntity playlistEntity = PlaylistEntity.builder().id("1").musics(new ArrayList<>()).build();
        Mockito.when(playlistRepositoryWithJpa.findById(Mockito.any())).thenReturn(Optional.of(playlistEntity));
        Mockito.when(musicRepository.findMusic(Mockito.any())).thenReturn(music1);


        List<Music> returnedMusics = playlistRepository.addMusics("1", musicList);
        Assertions.assertEquals(musicList, returnedMusics);
    }

}