package com.ciandt.summit.bootcamp2022.infra.entity;


import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome", columnDefinition = "TEXT")
    private String name;

    @JoinColumn(name = "PlaylistId")
    @ManyToOne(optional = false)
    private PlaylistEntity playlist;

    public UserEntity(String id, String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

}
