package com.ciandt.summit.bootcamp2022.entity.playlist;

import com.ciandt.summit.bootcamp2022.entity.music.Music;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistMusicsRepository {

    PlaylistMusics savePlaylist(Music music, Playlist playlist);

    void deleteMusicFromPlaylist(String playlistId, String musicId);

    Page<PlaylistMusicas> findByPlaylistId(String playlistId , Pageable pageable);
}
