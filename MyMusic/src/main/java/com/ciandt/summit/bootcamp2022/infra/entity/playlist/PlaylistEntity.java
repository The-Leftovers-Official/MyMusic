package com.ciandt.summit.bootcamp2022.infra.entity.playlist;


import com.ciandt.summit.bootcamp2022.entity.playlist.Playlist;
import com.ciandt.summit.bootcamp2022.infra.entity.music.MusicEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Builder
@Entity
@Table(name = "Playlists")
@Data
public class PlaylistEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;

    @OneToMany(mappedBy = "music")
    private List<PlaylistMusicas> musics = new ArrayList<>();

    public PlaylistEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public void addMusics(MusicEntity music) {
        PlaylistMusicas musics = PlaylistMusicas.builder().playlist(this)
                .music(music)
                .build();

        music.addIntoPlaylist(this);
        this.musics.add(musics);

    }

    public PlaylistEntity(Playlist playlist) {
        this.id = playlist.getId();
    }
}