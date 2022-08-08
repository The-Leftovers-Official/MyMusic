package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicsPKEntity;
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

        Artist artist = Artist.builder().id("1").name("artist").build();
        Music music = Music.builder().id("1").name("test").artist(artist).playlists(new ArrayList<>()).build();
        PlaylistEntity playlistEntity = PlaylistEntity.builder().id("1").musics(new ArrayList<>()).build();
        PlaylistMusicsPK pk = PlaylistMusicsPK.builder().playlistId(playlistEntity.getId()).musicId(music.getId()).build();

        Playlist playlist = Playlist.builder()
                        .id(playlistEntity.getId())
                        .musics(new ArrayList<>())
                        .build();
        PlaylistMusics playlistMusic = PlaylistMusics.builder().id(pk).music(music).playlist(playlist).build();

        Mockito.when(playlistRepositoryWithJpa.findById(Mockito.any())).thenReturn(Optional.of(playlistEntity));
        Mockito.when(musicRepository.findMusic(Mockito.any())).thenReturn(music);
        Mockito.when(playlistMusicsWithJpa.save(Mockito.any())).thenReturn(playlistMusic);

//        ModelMapper modelMapper = Mockito.mock(ModelMapper.class);
//        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(music);
        //Mockito.when(playlistMusicas.savePlaylist(Mockito.any(), Mockito.any())).thenReturn(playlistMusic);

        PlaylistMusics returnedMusic = playlistMusicas.savePlaylist(music, playlist);
        Assertions.assertEquals(playlistMusic, returnedMusic);
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