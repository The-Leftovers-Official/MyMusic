package com.ciandt.summit.bootcamp2022.entity;

import java.util.List;

public interface PlaylistRepository {

    Music addMusic(String playlistId, Music music);

    List<Music> addMusics(String playlistId, List<Music> convertIntoListMusic);
}
