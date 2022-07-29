package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.controller.dto.ResponseWrapper;
import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClientUtils;
import com.ciandt.summit.bootcamp2022.usecase.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/music")
@Validated
@Slf4j
public class MusicController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private TokenAuthorizedClientUtils tokenAuthorizedClient;

    @GetMapping
    @Cacheable(value = "listOfMusicByNameOrArtist")
    public ResponseEntity<ResponseWrapper> getMusicByNameOrArtist(@RequestParam(required = false) String filtro,
                                                                  @PageableDefault(page = 0, size = 30) Pageable pageable,
                                                                  @RequestHeader("Username") String username) {

        tokenAuthorizedClient.isAuthorized(username);

        if (filtro.isEmpty()) {
            log.info("Search without parameters accomplish.");
            return ResponseEntity.ok().body(new ResponseWrapper(MusicDto.converter(musicService.getAllData(pageable))));
        }

        if (filtro.length() < 2) {
            log.error("The filter parameter must be equal or greater than 2.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The filter parameter must be equal or greater than 2.");
        }

        Page<MusicDto> dataList = MusicDto.converter(musicService
                .getMusicByNameOrArtist(filtro, filtro, pageable));

        if (dataList.isEmpty()) {
            log.warn("No data found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(new ResponseWrapper(dataList));

    }
}
