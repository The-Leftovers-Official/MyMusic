package com.ciandt.summit.bootcamp2022.controller.dto;

import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MusicDto {

  private String id;
  private String nome;
  private ArtistDto artista;

  public MusicDto(MusicEntity musicEntity){
    this.id = musicEntity.getId();
    this.nome = musicEntity.getName();
    this.artista = new ArtistDto(musicEntity.getArtist());
  }

  public static List<MusicDto> converter(List<MusicEntity> musicEntity){
    return musicEntity.stream().map(MusicDto::new).collect(Collectors.toList());
  }

}
