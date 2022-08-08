package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.infra.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

//    @Test
//    public void shouldAddMusics (){
//
//        Artist artist = Artist.builder().id("1").name("artist").build();
//        Music music1 = Music.builder().id("1").name("music1").artist(artist).playlists(new ArrayList<>()).build();
//        Music music2 = Music.builder().id("1").name("music1").artist(artist).playlists(new ArrayList<>()).build();
//        List<Music> musicList = new ArrayList<>();
//        musicList.add(music1);
//        musicList.add(music2);
//        PlaylistEntity playlistEntity = PlaylistEntity.builder().id("1").musics(new ArrayList<>()).build();
//        PlaylistMusicsPKEntity pk = PlaylistMusicsPKEntity.builder().playlistId(playlistEntity.getId()).musicId(music1.getId()).build();
//        PlaylistMusicas playlistMusic = PlaylistMusicas.builder().id(pk).music(new MusicEntity(music1)).playlist(playlistEntity).build();
//
//        Mockito.when(playlistRepositoryWithJpa.findById(Mockito.any())).thenReturn(Optional.of(playlistEntity));
//        Mockito.when(musicRepository.findMusic(Mockito.any())).thenReturn(music1);
//        Mockito.when(playlistMusicsWithJpa.save(Mockito.any())).thenReturn(playlistMusic);
//
//
//        List<Music> returnedMusics = playlistRepository.addMusics("1", musicList);
//        Assertions.assertEquals(musicList, returnedMusics);
//        Assertions.assertEquals(2, returnedMusics.size());
//    }

}