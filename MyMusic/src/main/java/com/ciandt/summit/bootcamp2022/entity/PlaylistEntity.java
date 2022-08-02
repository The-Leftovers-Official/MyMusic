package com.ciandt.summit.bootcamp2022.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Playlists")
@Data
public class PlaylistEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;


    public PlaylistEntity() {
        this.id = UUID.randomUUID().toString();
        ;
    }
}
