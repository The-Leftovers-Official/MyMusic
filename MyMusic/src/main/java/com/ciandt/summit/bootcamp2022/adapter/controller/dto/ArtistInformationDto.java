package com.ciandt.summit.bootcamp2022.adapter.controller.dto;

import com.ciandt.summit.bootcamp2022.entity.artist.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ArtistInformationDto {
    @NotNull
    @NotEmpty
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String name;

    public Artist convert() {
        return Artist.builder()
                .id(this.id)
                .name(this.name).build();
    }
}
