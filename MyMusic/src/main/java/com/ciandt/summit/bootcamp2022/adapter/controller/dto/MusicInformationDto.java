package com.ciandt.summit.bootcamp2022.adapter.controller.dto;

import com.ciandt.summit.bootcamp2022.entity.music.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MusicInformationDto {
    private List<MusicInformationDataDto> data;

    public List<Music> convertIntoListMusic() {
        return data.stream().map(MusicInformationDataDto::convert).collect(Collectors.toList());
    }
}
