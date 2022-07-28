package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.controller.dto.ResponseWrapper;
import com.ciandt.summit.bootcamp2022.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
@Validated
public class MusicController {

    @Autowired
    private MusicRepositoryWithJpa musicRepositoryWithJpa;

    @GetMapping
    @Cacheable(value = "listOfMusicByNameOrArtist")
    public ResponseEntity<ResponseWrapper> getMusicByNameOrArtist(@RequestParam(required = false) String filtro) {

            if (filtro.isEmpty()) {
                return ResponseEntity.ok().body(new ResponseWrapper(MusicDto.converter(musicRepositoryWithJpa.findAll())));
            }

            if (filtro.length() < 2) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The filter parameter must be equal or greater than 2.");
            }

            List<MusicDto> dataList = MusicDto.converter(musicRepositoryWithJpa
                .findByNameContainingIgnoreCaseAndArtistNameContainingIgnoreCaseOrderByArtistNameAscName(filtro, filtro));

            if(dataList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok().body(new ResponseWrapper(dataList));

    }


}
