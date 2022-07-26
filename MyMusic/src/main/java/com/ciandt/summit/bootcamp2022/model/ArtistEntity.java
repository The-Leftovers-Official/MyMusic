package com.ciandt.summit.bootcamp2022.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Artistas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome")
    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<MusicEntity> musics = new ArrayList<>();


    public ArtistEntity(String name) {
        this.id = UUID.randomUUID().toString();;
        this.name = name;
    }

    public void addMusic(MusicEntity music){
        music.setArtist(this);
        this.musics.add(music);
    }
}
