package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.infra.entity.PlaylistMusicsPKEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistMusicsWithJpa extends JpaRepository<PlaylistMusicas, PlaylistMusicsPKEntity> {
}
