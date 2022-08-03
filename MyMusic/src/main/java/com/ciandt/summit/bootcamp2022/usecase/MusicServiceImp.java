package com.ciandt.summit.bootcamp2022.usecase;

import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;

@Service
public class MusicServiceImp implements MusicService{

  @Autowired
  private MusicRepositoryWithJpa musicRepositoryWithJpa;

  @Override
  public Page<MusicEntity> getMusicByNameOrArtist(String filtroMusica, String filtroArtista, Pageable pageable) {
    return musicRepositoryWithJpa
            .findByNameContainingIgnoreCaseOrArtistNameContainingIgnoreCaseOrderByArtistNameAscName(filtroMusica, filtroArtista, pageable);
  }

  @Override
  public Page<MusicEntity> getAllData(Pageable pageable) {
    return musicRepositoryWithJpa.findAll(pageable);
  }


}
