package com.ciandt.summit.bootcamp2022.adapter.controller;

import com.ciandt.summit.bootcamp2022.adapter.controller.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.adapter.controller.dto.ResponseWrapper;
import com.ciandt.summit.bootcamp2022.entity.music.Music;
import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClientUtils;
import com.ciandt.summit.bootcamp2022.infra.entity.music.MusicEntity;
import com.ciandt.summit.bootcamp2022.usecase.MusicService;
import com.ciandt.summit.bootcamp2022.usecase.MusicServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


@RestController
@RequestMapping("/api/v1/music")
@Validated
@Slf4j
public class MusicController {

    @Autowired
    private final MusicService musicService = new MusicServiceImp();

    @Autowired
    private TokenAuthorizedClientUtils tokenAuthorizedClient;

    @Operation(summary = "Get music by name or artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the music",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Music.class)) }),
            @ApiResponse(responseCode = "204", description = "No music found with these parameters",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Try to search with less than 2 letters parameter",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authorized",
                    content = @Content)})
    @GetMapping
    @Cacheable(value = "listOfMusicByNameOrArtist")
    public ResponseEntity<ResponseWrapper> getMusicByNameOrArtist(@Parameter(description = "filter can be name of the music or artist") @RequestParam(required = false) String filtro,
                                                                  @PageableDefault(page = 0) Pageable pageable,
                                                                  @RequestHeader("Username") String username) {

        tokenAuthorizedClient.isAuthorized(username);


        if (filtro == null || filtro.isEmpty()) {
            log.info("Search without parameters accomplish.");
            return ResponseEntity.ok().body(new ResponseWrapper(MusicDto.converter(musicService.getAllData(pageable))));
        }

        if (filtro.length() < 2) {
            log.error("The filter parameter must be equal or greater than 2.");
            throw new IllegalArgumentException( "The filter parameter must be equal or greater than 2.");
        }

        Page<MusicEntity> musicEntityPage = musicService.getMusicByNameOrArtist(filtro, filtro, pageable);

        Page<MusicDto> dataList = MusicDto.converter(musicEntityPage);

        if (dataList.isEmpty()) {
            log.warn("No data found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(new ResponseWrapper(dataList));

    }
}
