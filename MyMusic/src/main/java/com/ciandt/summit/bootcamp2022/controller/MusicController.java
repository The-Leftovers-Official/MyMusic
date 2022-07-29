package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.controller.dto.ResponseWrapper;
import com.ciandt.summit.bootcamp2022.usecase.MusicService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/music")
@Validated
@Log4j2
public class MusicController {


    //private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

    @Autowired
    private MusicService musicService;

    @GetMapping
    @Cacheable(value = "listOfMusicByNameOrArtist")
    public ResponseEntity<ResponseWrapper> getMusicByNameOrArtist(@RequestParam(required = false) String filtro) {


              Pageable pageable = PageRequest.of(0, 10);

              if (filtro.isEmpty()) {
                //logger.info("Teste de log");
                return ResponseEntity.ok().body(new ResponseWrapper(MusicDto.converter(musicService.getAllData(pageable))));
              }

              if (filtro.length() < 2) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The filter parameter must be equal or greater than 2.");
              }

              Page<MusicDto> dataList = MusicDto.converter(musicService
                      .getMusicByNameOrArtist(filtro, filtro, pageable));

              if (dataList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
              }

              return ResponseEntity.ok().body(new ResponseWrapper(dataList));

    }



}
