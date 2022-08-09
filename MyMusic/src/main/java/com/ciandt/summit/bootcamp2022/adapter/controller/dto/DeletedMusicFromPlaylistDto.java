package com.ciandt.summit.bootcamp2022.adapter.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Data
public class DeletedMusicFromPlaylistDto {

    private String playlistId;
    private String musicId;
    private String message;

    public DeletedMusicFromPlaylistDto(String playlistId, String musicId) {
        this.playlistId = playlistId;
        this.musicId = musicId;
        this.message  = "Successful deletion";
    }
}
