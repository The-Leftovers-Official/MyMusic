package com.ciandt.summit.bootcamp2022.adapter.controller.dto;

import com.ciandt.summit.bootcamp2022.infra.entity.music.MusicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MusicDto {


  private String id;
  private String name;
  private ArtistDto artist;


  public MusicDto(MusicEntity musicEntity){
    this.id = musicEntity.getId();
    this.name = musicEntity.getName();
    this.artist = new ArtistDto(musicEntity.getArtist());
  }

    public static Page<MusicDto> converter(Page<MusicEntity> musicEntity) {
      return musicEntity.map(MusicDto::new);
    }

}
