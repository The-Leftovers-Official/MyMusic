package com.ciandt.summit.bootcamp2022.entity;

public interface PlaylistMusicsRepository {

    PlaylistMusics savePlaylist(Music music, Playlist playlist);
    void deleteMusicFromPlaylist(String playlistId, String musicID);
}
