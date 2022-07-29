package com.ciandt.summit.bootcamp2022.usecase;

import com.ciandt.summit.bootcamp2022.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl implements MusicService{

  @Autowired
  private MusicRepositoryWithJpa musicRepositoryWithJpa;

  @Override
  public Page<MusicEntity> getMusicByNameOrArtist(String filtroMusica, String filtroArtista, Pageable pageable) {
    return musicRepositoryWithJpa
            .findByNameContainingIgnoreCaseOrArtistNameContainingIgnoreCaseOrderByArtistNameAscName(filtroMusica, filtroArtista, pageable);
  }

  @Override
  public Page<MusicEntity> getAllData(Pageable pageable) {
    return musicRepositoryWithJpa.findAllByOrderByArtistNameAscName(pageable);
  }


}
