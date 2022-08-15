package com.ciandt.summit.bootcamp2022.adapter.controller.dto;

import com.ciandt.summit.bootcamp2022.infra.entity.artist.ArtistEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class ArtistDto {

  private String id;
  private String name;

  public ArtistDto(ArtistEntity artistEntity){
    this.id = artistEntity.getId();
    this.name = artistEntity.getName();
  }

}
