package com.ciandt.summit.bootcamp2022.controller.dto;

import com.ciandt.summit.bootcamp2022.model.MusicEntity;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

public class DataDto {

  private List<MusicDto> data;

//  public static List<DataDto> converter(List<MusicDto> musicEntity){
//    return musicEntity.stream().map(DataDto::new).collect(Collectors.toList());
//  }

}
