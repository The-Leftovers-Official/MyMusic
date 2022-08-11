package com.ciandt.summit.bootcamp2022.adapter.controller.dto;

import com.ciandt.summit.bootcamp2022.infra.entity.playlist.PlaylistMusicas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaylistDto {
    private String playlistId;
    private MusicDto music;




    public PlaylistDto(PlaylistMusicas playlistMusicas) {
        this.playlistId = playlistMusicas.getPlaylist().getId();
        this.music = new MusicDto(playlistMusicas.getMusic());
    }

    public static List<PlaylistDto> converter(List<PlaylistMusicas> playlistMusicasList) {
        return playlistMusicasList.stream().map(PlaylistDto::new).collect(Collectors.toList());
    }
}
