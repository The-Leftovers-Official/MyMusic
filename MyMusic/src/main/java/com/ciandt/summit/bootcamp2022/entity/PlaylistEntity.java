package com.ciandt.summit.bootcamp2022.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Playlists")
@Data
public class PlaylistEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;


    public PlaylistEntity() {
        this.id = UUID.randomUUID().toString();;
    }
}
