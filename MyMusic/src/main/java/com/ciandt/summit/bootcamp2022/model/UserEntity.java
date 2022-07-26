package com.ciandt.summit.bootcamp2022.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome")
    private String name;

    @JoinColumn(name = "PlaylistId")
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<PlaylistEntity> playlists;

    public UserEntity(String id, String name) {
        this.id = UUID.randomUUID().toString();;
        this.name = name;
    }

    public void addPlaylists(PlaylistEntity playlist){
        this.playlists.add(playlist);
    }
}
