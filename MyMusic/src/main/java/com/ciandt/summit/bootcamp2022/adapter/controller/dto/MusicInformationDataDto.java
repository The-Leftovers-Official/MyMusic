package com.ciandt.summit.bootcamp2022.adapter.controller.dto;

import com.ciandt.summit.bootcamp2022.entity.music.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MusicInformationDataDto {
    @NotNull
    @NotEmpty
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private ArtistInformationDto artist;

    public Music convert() {
        return Music.builder()
                .id(this.id)
                .artist(this.getArtist().convert())
                .name(this.name)
                .playlists(new ArrayList<>()).build();
    }
}
