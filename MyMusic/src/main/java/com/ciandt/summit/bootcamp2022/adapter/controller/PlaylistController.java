package com.ciandt.summit.bootcamp2022.adapter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playlists/")
public class PlaylistController {

    @PostMapping("{playlistId}/musics")
    public void addMusicToPlaylist() {

    }
}
