package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.MusicRepository;
import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class MusicRepositoryImpl implements MusicRepository {

    private final MusicRepositoryWithJpa musicRepositoryWithJpa;

    private final ModelMapper modelMapper;

    @Override
    public Music findMusic(String id) {

        if (! musicRepositoryWithJpa.findById(id).isPresent()){
            throw new IllegalArgumentException("Music not found! ");
        }
        MusicEntity musicEntity = musicRepositoryWithJpa.findById(id).get();
        return modelMapper.map(musicEntity, Music.class);
    }
}
