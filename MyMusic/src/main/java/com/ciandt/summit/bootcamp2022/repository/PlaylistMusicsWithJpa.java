package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicsPKEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PlaylistMusicsWithJpa extends JpaRepository<PlaylistMusicas, PlaylistMusicsPKEntity> {

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM PlaylistMusicas WHERE PlaylistMusicas.PlaylistId = :playlistId AND PlaylistMusicas.MusicaId = :musicId", nativeQuery = true)
  void deleteMusicFromPlaylist(String playlistId, String musicId);

}
