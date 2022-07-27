package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
@Validated
public class MusicController {

    @Autowired
    private MusicRepositoryWithJpa musicRepositoryWithJpa;

//    @GetMapping
//    public ResponseEntity<String> get() {
//        return ResponseEntity.ok("67f5976c-eb1e-404e-8220-2c2a8a23be47");
//    }

    @GetMapping
    public ResponseEntity<List<MusicDto>> getMusicByNameOrArtist(@RequestParam(required = false) String filtro) {
        List<MusicEntity> listOfMusic = musicRepositoryWithJpa.findByArtistNameContainingIgnoreCaseOrderByArtistNameAsc(filtro);
        return ResponseEntity.ok().body(MusicDto.converter(listOfMusic));

    }

    @GetMapping("/teste")
    public List<MusicEntity> testSearch(String name){
//        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC), "")
        List<MusicEntity> listOfMusics = musicRepositoryWithJpa.findAll();
        return  listOfMusics;
    }



}
