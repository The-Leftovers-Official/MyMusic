package com.ciandt.summit.bootcamp2022.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@Builder
@Data
public class Playlist {
    private String id;

    private List<PlaylistMusics> musics = new ArrayList<>();

    public Playlist() {
        this.id = UUID.randomUUID().toString();
    }

    public void addMusics(Music music) {
        PlaylistMusics musics = PlaylistMusics.builder().playlist(this)
                .music(music)
                .build();

        this.musics.add(musics);

    }
}
