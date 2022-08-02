package com.ciandt.summit.bootcamp2022.entity;

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
public class PlaylistMusics {

    @EmbeddedId
    PlaylistMusicsPK id;

    @ManyToOne
    @MapsId("playlistId")
    @JoinColumn(name = "PlaylistId")
    PlaylistEntity playlist;

    @ManyToOne
    @MapsId("musicId")
    @JoinColumn(name = "MusicaId")
    MusicEntity music;

}
