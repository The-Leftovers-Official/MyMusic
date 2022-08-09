package com.ciandt.summit.bootcamp2022.adapter.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeletedMusicFromPlaylistDto {

    private String playlistId;
    private String musicId;
    private static String message = "Successful deletion";
}
