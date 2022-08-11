package com.ciandt.summit.bootcamp2022.adapter.controller;

import com.ciandt.summit.bootcamp2022.adapter.controller.dto.DeletedMusicFromPlaylistDto;
import com.ciandt.summit.bootcamp2022.adapter.controller.dto.MusicInformationDto;
import com.ciandt.summit.bootcamp2022.adapter.controller.dto.ResponseWrapper;
import com.ciandt.summit.bootcamp2022.entity.music.Music;
import com.ciandt.summit.bootcamp2022.entity.playlist.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.exceptions.AuthorizedHandler;
import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClientUtils;
import com.ciandt.summit.bootcamp2022.repository.PlaylistMusicasImpl;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepositoryImpl;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/playlists/")
public class PlaylistController {

    @Autowired
    private TokenAuthorizedClientUtils tokenAuthorizedClient;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistMusicasImpl playlistMusicsRepository;


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
    public ResponseEntity<MusicInformationDto> addMusicToPlaylist(@Parameter(description = "Id of the playlist where you want to add a music") @PathVariable String playlistId,
                                                              @RequestBody MusicInformationDto musicInformationDto,
                                                              @Parameter(description = "Username to get authorization") @RequestHeader("Username") String username) {

        if (username.isEmpty())
            throw new AuthorizedHandler.InvalidRequestHeaderException();


        tokenAuthorizedClient.isAuthorized(username);

        if(musicInformationDto.getData().size() == 1) {
            playlistRepository.addMusic(playlistId, musicInformationDto.convertIntoListMusic().get(0));
        }else {
            playlistRepository.addMusics(playlistId, musicInformationDto.convertIntoListMusic());
        }


        return ResponseEntity.ok(musicInformationDto);
    }


    @Operation(summary = "Delete music from playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful deletion",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Music or playlist don't exists on database",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authorized",
                    content = @Content)})
    @DeleteMapping("/{playlistId}/musics/{musicId}")
    @Transactional
    public ResponseEntity<DeletedMusicFromPlaylistDto> deleteMusicFromPlaylist(@PathVariable @NotNull @NotEmpty String playlistId,
                                                                                @PathVariable @NotNull @NotEmpty String musicId,
                                                                                @RequestHeader("Username") String username ) throws IllegalArgumentException{
        if (username.isEmpty())
            throw new AuthorizedHandler.InvalidRequestHeaderException();

        tokenAuthorizedClient.isAuthorized(username);

        playlistMusicsRepository.deleteMusicFromPlaylist(playlistId, musicId);

        return ResponseEntity.ok().body(new DeletedMusicFromPlaylistDto(playlistId, musicId));

    }
}