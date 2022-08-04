package com.ciandt.summit.bootcamp2022.adapter.controller;

import com.ciandt.summit.bootcamp2022.adapter.controller.dto.MusicInformationDto;
import com.ciandt.summit.bootcamp2022.adapter.controller.dto.ResponseWrapper;
import com.ciandt.summit.bootcamp2022.entity.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClientUtils;
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

    @PostMapping("{playlistId}/musics")
    @Transactional
    public ResponseEntity<ResponseWrapper> addMusicToPlaylist(@PathVariable String playlistId,
                                                              @RequestBody MusicInformationDto musicInformationDto,
                                                              @RequestHeader("Username") String username) {

        tokenAuthorizedClient.isAuthorized(username);

        if(musicInformationDto.getData().size() == 1) {
            playlistRepository.addMusic(playlistId, musicInformationDto.convertIntoListMusic().get(0));
        }

        playlistRepository.addMusics(playlistId, musicInformationDto.convertIntoListMusic());



        return ResponseEntity.ok(new ResponseWrapper<>(musicInformationDto));
    }
}