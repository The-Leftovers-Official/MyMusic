package com.ciandt.summit.bootcamp2022.entity;

import com.ciandt.summit.bootcamp2022.infra.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistEntity;

public interface PlaylistRepository {

    Playlist addMusic(Music music);
}
