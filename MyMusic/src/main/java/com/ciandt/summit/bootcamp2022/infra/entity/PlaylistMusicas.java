package com.ciandt.summit.bootcamp2022.infra.entity;

import com.ciandt.summit.bootcamp2022.entity.PlaylistMusics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PlaylistMusicas {

    @EmbeddedId
    PlaylistMusicsPKEntity id;

    @ManyToOne
    @MapsId("playlistId")
    @JoinColumn(name = "PlaylistId")
    PlaylistEntity playlist;

    @ManyToOne
    @MapsId("musicId")
    @JoinColumn(name = "MusicaId")
    MusicEntity music;

    public PlaylistMusicas(PlaylistMusics playlistMusics) {
        this.playlist = playlist;
        this.music = music;
    }
}
