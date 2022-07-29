package com.ciandt.summit.bootcamp2022.controller;


import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MusicControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MusicRepositoryWithJpa musicRepositoryWithJpa;

  public static final String urlTemplate = "/api/v1/music";

  @Test
  public void shouldReturnArtistAndMusicWhenParameterIsGreaterOrEqualTwo() throws Exception {

    String filtro = "AB";
    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").isNotEmpty())
            .andExpect(jsonPath("$.data.content[0].artista.nome").value("ABBA"));

  }

  @Test
  public void shouldReturn204WhenNoDataIsFoundWithEspecifiedParameters() throws Exception {

    String filtro = "asdasdasdasd";

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isNoContent());

  }
  @Test
  public void shouldReturn400WhenParameterIsLessThan2Characters() throws Exception {

    String filtro = "A";

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isBadRequest());

  }

  @Test
  public void shouldReturnAllDataWhenNoParameterIsPassed() throws Exception {

    String filtro = "";

    mockMvc
            .perform(get(urlTemplate)
                    .param("filtro", filtro))
            .andExpect(status().isOk());

  }

}