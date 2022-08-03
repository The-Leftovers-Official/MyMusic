package com.ciandt.summit.bootcamp2022.infra.entity;


import com.ciandt.summit.bootcamp2022.entity.Playlist;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Playlists")
@Data
public class PlaylistEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<PlaylistMusicas> musics = new ArrayList<>();

    public PlaylistEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public void addMusics(MusicEntity music) {
        PlaylistMusicas musics = PlaylistMusicas.builder().playlist(this)
                .music(music)
                .build();

        this.musics.add(musics);

    }

    public PlaylistEntity(Playlist playlist) {
        this.id = playlist.getId();
    }
}
