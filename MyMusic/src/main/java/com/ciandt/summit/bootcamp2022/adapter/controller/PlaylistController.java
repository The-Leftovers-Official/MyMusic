package com.ciandt.summit.bootcamp2022.adapter.controller;

import com.ciandt.summit.bootcamp2022.adapter.controller.dto.MusicInformationDto;
import com.ciandt.summit.bootcamp2022.adapter.controller.dto.ResponseWrapper;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.exceptions.AuthorizedHandler;
import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClientUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/playlists/")
public class PlaylistController {

    @Autowired
    private TokenAuthorizedClientUtils tokenAuthorizedClient;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Operation(summary = "Add music into playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Music added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Music.class)) }),
            @ApiResponse(responseCode = "400", description = "Music or playlist don't exists on database",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authorized",
                    content = @Content)})
    @PostMapping("{playlistId}/musics")
    @Transactional
    public ResponseEntity<ResponseWrapper> addMusicToPlaylist(@Parameter(description = "Id of the playlist where you want to add a music") @PathVariable String playlistId,
                                                              @RequestBody MusicInformationDto musicInformationDto,
                                                              @Parameter(description = "Username to get authorization") @RequestHeader("Username") String username) {

        if (username.isEmpty())
            throw new AuthorizedHandler.InvalidRequestHeaderException();


        tokenAuthorizedClient.isAuthorized(username);

        if(musicInformationDto.getData().size() == 1) {
            playlistRepository.addMusic(playlistId, musicInformationDto.convertIntoListMusic().get(0));
        }

        playlistRepository.addMusics(playlistId, musicInformationDto.convertIntoListMusic());



        return ResponseEntity.ok(new ResponseWrapper<>(musicInformationDto));
    }
}