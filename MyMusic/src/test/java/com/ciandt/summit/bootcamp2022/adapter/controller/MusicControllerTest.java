package com.ciandt.summit.bootcamp2022.adapter.controller;


import com.ciandt.summit.bootcamp2022.adapter.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClient;
import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClientUtils;
import com.ciandt.summit.bootcamp2022.infra.entity.artist.ArtistEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.music.MusicEntity;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import com.ciandt.summit.bootcamp2022.usecase.MusicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(MusicController.class)
public class MusicControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MusicRepositoryWithJpa musicRepositoryWithJpa;
  @MockBean
  private MusicService musicService;

  @MockBean
  private TokenAuthorizedClientUtils tokenAuthorizedClientUtils;

  @MockBean
  private TokenAuthorizedClient tokenAuthorizedClient;

  @MockBean
  private MusicDto musicDto;

  private ArtistEntity ARTIST;
  private MusicEntity MUSIC;

  public static final String urlTemplate = "/api/v1/music";


  @BeforeEach
  void setUp(){
    ARTIST = ArtistEntity.builder()
            .id("1")
            .name("Celine Dion")
            .build();

    MUSIC = MusicEntity.builder()
            .id("1")
            .name("All By Myself")
            .playlists(new ArrayList<>())
            .artist(ARTIST)
            .build();
  }

  // TODO: Erro de autorização
  @Test
  public void shouldReturnNotAuthorized() throws Exception {
    String filtro = "AB";

    Mockito.when(musicService.getMusicByNameOrArtist(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(Page.empty());

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isUnauthorized());
  }



  //TODO: Buscar + de 2 caracteres deve retornar Artista E Música
  @Test
  public void shouldReturnArtistAndMusicWhenParameterIsGreaterOrEqualTwo() throws Exception {
      String filtro = "Ce";

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

      List<MusicEntity> musicEntities = new ArrayList<>();

      musicEntities.add(MUSIC);
      musicEntities.add(thePowerOfLove);
      musicEntities.add(iAmAlive);

      Page<MusicEntity> pageMusics = new PageImpl<>(musicEntities);

      Mockito.when(musicService.getMusicByNameOrArtist(Mockito.any(),Mockito.any(),Mockito.any()))
              .thenReturn(pageMusics);

      mockMvc
              .perform(get(urlTemplate)
                      .param("filtro", filtro)
                      .header("Username", "teste"))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.data.content.length()").value(3))
              .andExpect(jsonPath("$.data.content[0].id").value("1"))
              .andExpect(jsonPath("$.data.content[1].id").value("2"));
  }


//  // TODO: Deve Retornar 204 caso não encontre
  @Test
  public void shouldReturn204WhenNoDataIsFoundWithEspecifiedParameters() throws Exception {

      String filtro = "AB";

      Mockito.when(musicService.getMusicByNameOrArtist(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(Page.empty());

      mockMvc
              .perform(get(urlTemplate)
                      .param("filtro", filtro)
                      .header("Username", "teste"))
              .andExpect(status().isNoContent());

  }

  // TODO: Deve Retornar 400 caso envie apenas 1 caracter
  @Test
  public void shouldReturn400WhenParameterIsLessThan2Characters() throws Exception {

    String filtro = "A";

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro)
                    .header("Username", "teste"))
            .andExpect(status().isBadRequest());

  }

  // TODO: Deve Retornar todos os registros caso o filtro esteja vazio
  @Test
  public void shouldReturnAllDataWhenNoParameterIsPassed() throws Exception {

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

    List<MusicEntity> musicEntities = new ArrayList<>();

    musicEntities.add(MUSIC);
    musicEntities.add(thePowerOfLove);
    musicEntities.add(iAmAlive);

    Page<MusicEntity> pageMusics = new PageImpl<>(musicEntities);

    Mockito.when(musicService.getAllData(Mockito.any(Pageable.class)))
            .thenReturn(pageMusics);

    mockMvc
            .perform(get(urlTemplate)
                    .header("Username", "teste"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.content.length()").value(3))
            .andExpect(jsonPath("$.data.content[0].id").value("1"))
            .andExpect(jsonPath("$.data.content[1].id").value("2"));


  }

  // TODO: Busca deve vir ordenada pelo nome do artista e depois pelo nome da música


}