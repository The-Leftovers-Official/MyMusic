package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicsPKEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PlaylistMusicsWithJpa extends JpaRepository<PlaylistMusicas, PlaylistMusicsPKEntity> {

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM PlaylistMusicas WHERE PlaylistMusicas.PlaylistId = :playlistId AND PlaylistMusicas.MusicaId = :musicId", nativeQuery = true)
  void deleteMusicFromPlaylist(String playlistId, String musicId);


  Page<PlaylistMusicas> findByPlaylistId(String playlistId, Pageable pageable);


}
