package com.ciandt.summit.bootcamp2022.infra.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class PlaylistMusicsPKEntity implements Serializable {

    @Column(name = "PlaylistId")
    private String playlistId;

    @Column(name = "MusicaId")
    private String musicId;

}
