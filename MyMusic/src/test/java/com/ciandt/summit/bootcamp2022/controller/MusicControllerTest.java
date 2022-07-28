package com.ciandt.summit.bootcamp2022.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MusicControllerTest {

  @Autowired
  private MockMvc mockMvc;

  //TODO: Buscar + de 2 caracteres deve retornar Artista E Música

  // TODO: Deve Retornar 204 caso não encontre

  // TODO: Deve Retornar 400 caso envie apenas 1 caracter

  // TODO: Busca deve vir ordenada pelo nome do artista e depois pelo nome da música

  @Test
  public void shouldReturnArtistAndMusicWhenParameterIsGreaterOrEqualTwo() throws Exception {

    String filtro = "AB";

    mockMvc
            .perform(get("/api/v1/music")
                    .param("filtro", filtro))
            .andExpect(status().isOk());

  }

}