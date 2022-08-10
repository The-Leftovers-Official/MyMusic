package com.ciandt.summit.bootcamp2022.entity.playlist;

import com.ciandt.summit.bootcamp2022.entity.music.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlaylistMusics {

    PlaylistMusicsPK id;

    Playlist playlist;

    Music music;
}
