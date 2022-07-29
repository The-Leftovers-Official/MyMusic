package com.ciandt.summit.bootcamp2022.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Artistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome", columnDefinition = "TEXT")
    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<MusicEntity> musics = new ArrayList<>();


    public ArtistEntity(String name) {
        this.id = UUID.randomUUID().toString();
        ;
        this.name = name;
    }

    public void addMusic(MusicEntity music) {
        music.setArtist(this);
        this.musics.add(music);
    }
}
