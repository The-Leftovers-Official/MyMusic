package com.ciandt.summit.bootcamp2022.model;


import lombok.*;

import javax.persistence.*;
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ArtistEntity artist;

    public MusicEntity(String name, ArtistEntity artist) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.artist = artist;
    }
}
