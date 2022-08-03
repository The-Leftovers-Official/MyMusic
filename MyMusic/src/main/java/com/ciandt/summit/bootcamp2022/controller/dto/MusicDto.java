package com.ciandt.summit.bootcamp2022.controller.dto;

import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

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

    public static Page<MusicDto> converter(Page<MusicEntity> musicEntity) {
      return musicEntity.map(MusicDto::new);
    }

}
