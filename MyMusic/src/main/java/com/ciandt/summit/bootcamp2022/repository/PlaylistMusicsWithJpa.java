package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicsPKEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaylistMusicsWithJpa extends JpaRepository<PlaylistMusicas, PlaylistMusicsPKEntity> {

  @Query(value = "DELETE FROM PlaylistMusicas p WHERE p.PlaylistId = :playlistId AND p.MusicaId = :musicId", nativeQuery = true)
  void deleteMusicFromPlaylist(String playlistId, String musicId);

}
