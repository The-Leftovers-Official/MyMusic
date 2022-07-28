package com.ciandt.summit.bootcamp2022.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import com.ciandt.summit.dto.MusicDto;

@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    @Autowired
    private MusicRepositoryWithJpa musicRepository;

    @GetMapping
    public ResponseEntity<Page<MusicDto>> index() {
        Pageable pagination = PageRequest.of(0, 5);
        Page<MusicEntity> musics = musicRepository.getAllMusics(pagination);
        return ResponseEntity.ok(MusicDto.convert(musics));
    }
}
