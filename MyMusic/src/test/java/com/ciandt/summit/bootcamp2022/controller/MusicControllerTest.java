package com.ciandt.summit.bootcamp2022.controller;


import com.ciandt.summit.bootcamp2022.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MusicControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MusicRepositoryWithJpa musicRepositoryWithJpa;

  public static final String urlTemplate = "/api/v1/music";


  //TODO: Buscar + de 2 caracteres deve retornar Artista E Música
  @Test
  public void shouldReturnArtistAndMusicWhenParameterIsGreaterOrEqualTwo() throws Exception {

    String filtro = "AB";
    Pageable pageable = PageRequest.of(0, 10);

    Page<MusicEntity> musicsSearched = musicRepositoryWithJpa.findByNameContainingIgnoreCaseOrArtistNameContainingIgnoreCaseOrderByArtistNameAscName(filtro, filtro, pageable);

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isOk());

    /*assertThat(musicsSearched)
            .extracting(MusicEntity::getId, MusicEntity::getName, MusicEntity::getArtist)
            .contains(
                    tuple("738ba840-ddf0-44a5-b19e-5ca8a498b9b0", "A Nazi Song, Shouts Of Seig Heill And Adolph Hitler Speaking"));
*/
    /*"artista": {
      "id": "2154a968-f48c-4890-a70f-a2c552c84b71",
              "nome": "ABBA"
    }*/

  }


  // TODO: Deve Retornar 204 caso não encontre
  @Test
  public void shouldReturn204WhenNoDataIsFoundWithEspecifiedParameters() throws Exception {

    String filtro = "asdasdasdasd";

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isNoContent());

  }

  // TODO: Deve Retornar 400 caso envie apenas 1 caracter
  @Test
  public void shouldReturn400WhenParameterIsLessThan2Characters() throws Exception {

    String filtro = "A";

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isBadRequest());

  }

  // TODO: Deve Retornar todos os registros caso o filtro esteja vazio
  @Test
  public void shouldReturnAllDataWhenNoParameterIsPassed() throws Exception {

    String filtro = "";

    Pageable pageable = PageRequest.of(0, 10);
    Page<MusicEntity> musicsSearched = musicRepositoryWithJpa.findAll(pageable);

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isOk());

    assertThat(musicsSearched)
            .hasSize(10)
            .extracting(MusicEntity::getId, MusicEntity::getName, MusicEntity::getArtist);

  }

  // TODO: Busca deve vir ordenada pelo nome do artista e depois pelo nome da música


}