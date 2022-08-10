package com.ciandt.summit.bootcamp2022.entity.playlist;

import com.ciandt.summit.bootcamp2022.entity.music.Music;

public interface PlaylistMusicsRepository {

    PlaylistMusics savePlaylist(Music music, Playlist playlist);

    void deleteMusicFromPlaylist(String playlistId, String musicId);
}
