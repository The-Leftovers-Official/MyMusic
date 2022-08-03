package com.ciandt.summit.bootcamp2022.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Music {
    
    private String id;
    private String name;
    private Artist artist;
    private List<PlaylistMusics> playlists = new ArrayList<>();

    public Music(String name, Artist artist) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.artist = artist;
        this.playlists = new ArrayList<>();
    }

    public void addIntoPlaylist(Playlist playlist) {
        PlaylistMusics newPlaylist = PlaylistMusics.builder().music(this)
                .playlist(playlist)
                .build();

        this.playlists.add(newPlaylist);
    }
}
