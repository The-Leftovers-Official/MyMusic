package com.ciandt.summit.bootcamp2022.infra.entity.artist;


import com.ciandt.summit.bootcamp2022.entity.artist.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Artistas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome", columnDefinition = "TEXT")
    private String name;

    public ArtistEntity(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public ArtistEntity(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
    }
}
