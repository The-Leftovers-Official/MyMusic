package com.ciandt.summit.bootcamp2022.adapter.controller.dto;

import com.ciandt.summit.bootcamp2022.entity.ArtistEntity;
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
  private String nome;

  public ArtistDto(ArtistEntity artistEntity){
    this.id = artistEntity.getId();
    this.nome = artistEntity.getName();
  }

}
