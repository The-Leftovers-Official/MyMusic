package com.ciandt.summit.bootcamp2022.entity.playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlaylistMusicsPK {

    private String playlistId;

    private String musicId;
}
