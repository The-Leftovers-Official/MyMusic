package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepositoryWithJpa extends JpaRepository<PlaylistEntity, String>{


}
