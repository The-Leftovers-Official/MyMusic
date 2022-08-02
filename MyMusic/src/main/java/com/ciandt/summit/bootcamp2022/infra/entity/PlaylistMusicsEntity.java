package com.ciandt.summit.bootcamp2022.infra.entity;

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
public class PlaylistMusicsEntity {

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

}
