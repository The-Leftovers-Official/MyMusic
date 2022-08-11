package com.ciandt.summit.bootcamp2022.entity.playlist;

import com.ciandt.summit.bootcamp2022.entity.music.Music;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicas;

import java.util.List;

public interface PlaylistMusicsRepository {

    PlaylistMusics savePlaylist(Music music, Playlist playlist);

    void deleteMusicFromPlaylist(String playlistId, String musicId);

    List<PlaylistMusicas> findByPlaylistId(String playlistId );
}
