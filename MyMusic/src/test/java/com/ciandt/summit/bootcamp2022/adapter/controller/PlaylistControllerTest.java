package com.ciandt.summit.bootcamp2022.adapter.controller;

import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClient;
import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClientUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlaylistControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TokenAuthorizedClientUtils tokenAuthorizedClientUtils;

  @MockBean
  private TokenAuthorizedClient tokenAuthorizedClient;

  String json = "{\n" +
          "  \"data\": [\n" +
          "  {\n" +
          "     \"id\": \"fc615aa1-8f3a-499b-a422-9655d4a29006\",\n" +
          "     \"name\": \"Ani Na'amin\",\n" +
          "     \"artist\": {\n" +
          "       \"id\": \"2154a968-f48c-4890-a70f-a2c552c84b71\",\n" +
          "       \"name\": \"ABBA\" \n" +
          "      } \n" +
          "  }\n" +
          "]}";

  String jsonMusicList = "{\n" +
          "  \"data\": [\n" +
          "  {\n" +
          "     \"id\": \"fc615aa1-8f3a-499b-a422-9655d4a29006\",\n" +
          "     \"name\": \"Ani Na'amin\",\n" +
          "     \"artist\": {\n" +
          "       \"id\": \"2154a968-f48c-4890-a70f-a2c552c84b71\",\n" +
          "       \"name\": \"ABBA\" \n" +
          "      } \n" +
          "  },\n" +
          "  {\n" +
          "     \"id\": \"7b6fd113-d02c-49bb-9d0c-1abc57777c72\",\n" +
          "     \"name\": \"A National Acrobat\",\n" +
          "     \"artist\": {\n" +
          "       \"id\": \"92d31423-e6c4-481c-9491-6a9559a4e0cc\",\n" +
          "       \"name\":\"Black Sabbath\" \n" +
          "      } \n" +
          "  }\n" +
          "]}";

  String jsonMusicThatNotExists = "{\n" +
          "  \"data\": [\n" +
          "  {\n" +
          "     \"id\": \"1\",\n" +
          "     \"name\": \"Ani Na'amin\",\n" +
          "     \"artist\": {\n" +
          "       \"id\": \"2154a968-f48c-4890-a70f-a2c552c84b71\",\n" +
          "       \"name\": \"ABBA\" \n" +
          "      } \n" +
          "  }\n" +
          "]}";

  String jsonOneOfTheMusicsThatNotExists = "{\n" +
          "  \"data\": [\n" +
          "  {\n" +
          "     \"id\": \"455bb60e-d066-4c70-9a83-43984d8fa4e6\",\n" +
          "     \"name\": \"Don't Need It\",\n" +
          "     \"artist\": {\n" +
          "       \"id\": \"ecabe637-ae65-4b33-bb37-ea333093a9d2\",\n" +
          "       \"name\": \"Bad Brains\"\n" +
          "      } \n" +
          "  },\n" +
          "  {\n" +
          "     \"id\": \"239ae38c-f18c-4a10-887f-522067b02347\",\n" +
          "     \"name\": \"Jokerman\",\n" +
          "     \"artist\": {\n" +
          "       \"id\": \"716923cd-5fd3-4421-8c8e-bafed1f95ae8\",\n" +
          "       \"name\":\"Bob Dylan\"\n" +
          "      } \n" +
          "  },\n" +
          "{\n" +
          "     \"id\": \"239ae38c-f18c-4a10-5345534533534\",\n" +
          "     \"name\": \"Roll On John\",\n" +
          "     \"artist\": {\n" +
          "       \"id\": \"716923cd-5fd3-4421-8c8e-bafed1f95ae8\",\n" +
          "       \"name\":\"Bob Dylan\"\n" +
          "      } \n" +
          "  }\n" +
          "]}";
  public static final String urlTemplate = "/api/playlists/e643958a-f388-4c0c-ab90-787336a61ae1/musics";

  //TODO: Erro de autorização (token)
  @Test
  public void shouldReturnNotAuthorized() throws Exception {

    mockMvc
            .perform(MockMvcRequestBuilders.post(urlTemplate)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());

  }

  //TODO: Retorna 200 quando uma música é adicionada à playlist
  @Test
  public void shouldReturn200WhenAddANewMusicIsAddedOnPlaylist() throws  Exception  {

    mockMvc
            .perform(MockMvcRequestBuilders.post(urlTemplate)
                    .header("Username", "teste")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

  }

  //TODO: Deve retornar 200 quando uma lista de músicas é adicionada à playlist
  @Test
  public void shouldReturn200WhenAddANewListOfMusicIsAddedOnPlaylist() throws  Exception  {

    mockMvc
            .perform(MockMvcRequestBuilders.post(urlTemplate)
                    .header("Username", "teste")
                    .content(jsonMusicList)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

  }

  //TODO: Retorna 400 quando tento adicionar uma música inexistente à playlist
  @Test
  public void shouldReturn400WhenTheMusicNotExists() throws  Exception  {

    mockMvc
            .perform(MockMvcRequestBuilders.post(urlTemplate)
                    .header("Username", "teste")
                    .content(jsonMusicThatNotExists)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());

  }

  //TODO: Retorna 400 quando tento adicionar uma música existente à uma playlist inexistente
  @Test
  public void shouldReturn400WhenTheMusicExistsButPlaylistNotExists() throws  Exception  {

    mockMvc
            .perform(MockMvcRequestBuilders.post("/api/playlists/1/musics")
                    .header("Username", "teste")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());

  }

  //TODO: Retorna 400 quando tento adicionar uma lista de músicas e uma ou mais músicas não existem
  @Test
  public void shouldReturn400WhenOneOfTheMusicsOnRequestDoNotExists() throws  Exception  {

    mockMvc
            .perform(MockMvcRequestBuilders.post(urlTemplate)
                    .header("Username", "teste")
                    .content(jsonOneOfTheMusicsThatNotExists)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());

  }



}