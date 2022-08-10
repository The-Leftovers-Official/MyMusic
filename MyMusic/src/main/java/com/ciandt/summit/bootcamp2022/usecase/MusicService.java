package com.ciandt.summit.bootcamp2022.usecase;

import com.ciandt.summit.bootcamp2022.infra.entity.music.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MusicService {

  Page<MusicEntity> getMusicByNameOrArtist(String filtroMusica, String filtroArtista, Pageable pageable);

  Page<MusicEntity> getAllData(Pageable pageable);



}
