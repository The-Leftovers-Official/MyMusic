package com.ciandt.summit.bootcamp2022.usecase;

import com.ciandt.summit.bootcamp2022.infra.entity.artist.ArtistEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.music.MusicEntity;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
class MusicServiceImpTest {


    @Autowired
    private EntityManager entityManager;
    private MusicEntity MUSIC;

    private ArtistEntity ARTIST;

    @InjectMocks
    private MusicServiceImp musicServiceImp;

    @Mock
    private MusicRepositoryWithJpa musicRepositoryWithJpa;

    @BeforeEach
    public void setup(){

        ARTIST = ArtistEntity.builder()
                .id("1")
                .name("Celine Dion")
                .build();

        entityManager.persist(ARTIST);

        MUSIC = MusicEntity.builder()
                .id("1")
                .name("All By Myself")
                .playlists(new ArrayList<>())
                .artist(ARTIST)
                .build();

        entityManager.persist(MUSIC);

    }

    @Test
    public void shouldReturnMusicByName(){

        String filter = "All";

        List<MusicEntity> musicEntities = new ArrayList<>();
        musicEntities.add(MUSIC);

        Page<MusicEntity> pageMusic = new PageImpl<MusicEntity>(musicEntities);

        Mockito.when(musicRepositoryWithJpa.findByNameContainingIgnoreCaseOrArtistNameContainingIgnoreCaseOrderByArtistNameAscName(Mockito.any(), Mockito.any(), Mockito.any()))
                        .thenReturn(pageMusic);

        Assertions.assertEquals(pageMusic, musicServiceImp.getMusicByNameOrArtist(filter, filter, Pageable.ofSize(1)));

    }

    @Test
    public void shouldReturn3Musics(){

        MusicEntity thePowerOfLove = MusicEntity.builder()
                .id("2")
                .name("The Power of Love")
                .playlists(new ArrayList<>())
                .artist(ARTIST)
                .build();

        MusicEntity iAmAlive = MusicEntity.builder()
                .id("3")
                .name("I'm Alive")
                .playlists(new ArrayList<>())
                .artist(ARTIST)
                .build();

        entityManager.persist(thePowerOfLove);
        entityManager.persist(iAmAlive);


        List<MusicEntity> musicEntities = new ArrayList<>();

        musicEntities.add(MUSIC);
        musicEntities.add(thePowerOfLove);
        musicEntities.add(iAmAlive);

        Page<MusicEntity> pageMusics = new PageImpl<>(musicEntities);

        Mockito.when(musicRepositoryWithJpa.findAll(Mockito.any(Pageable.class)))
                .thenReturn(pageMusics);

        Assertions.assertEquals(pageMusics,
                musicServiceImp.getAllData(Pageable.ofSize(1)));

    }

}