package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.controller.dto.DataDto;
import com.ciandt.summit.bootcamp2022.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.model.MusicEntity;
import com.ciandt.summit.bootcamp2022.repository.MusicRepositoryWithJpa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


            if (filtro.isEmpty()) {
                return ResponseEntity.ok().body(MusicDto.converter(musicRepositoryWithJpa.findAll()));
            }

            if (filtro.length() < 2) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The filter parameter must be equal or greater than 2.");
            }


            List<MusicDto> musicList = MusicDto.converter(musicRepositoryWithJpa
                .findByNameContainingIgnoreCaseOrArtistNameContainingIgnoreCaseOrderByArtistNameAscName(filtro, filtro));

            if(musicList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok().body(musicList);


    }


}
