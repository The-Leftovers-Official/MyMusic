package com.ciandt.summit.bootcamp2022.infra.entity;


import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Musicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome", columnDefinition = "TEXT")
    private String name;

    @JoinColumn(name = "ArtistaId")
    @ManyToOne
    private ArtistEntity artist;

    @OneToMany(mappedBy = "playlist")
    private List<PlaylistMusicas> playlists = new ArrayList<>();

    public MusicEntity(String name, ArtistEntity artist) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.artist = artist;
    }

    public MusicEntity(Music music) {
        this.id = music.getId();
        this.name = music.getName();
        this.artist = new ArtistEntity(music.getArtist());
        //this.playlists = music.getPlaylists().stream().map(PlaylistEntity::new);
    }

    public void addIntoPlaylist(PlaylistEntity playlist) {
        PlaylistMusicas newPlaylist = PlaylistMusicas.builder().music(this)
                .playlist(playlist)
                .build();

        this.playlists.add(newPlaylist);
    }

}
