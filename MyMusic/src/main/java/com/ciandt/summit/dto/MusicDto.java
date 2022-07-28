package com.ciandt.summit.dto;

import org.springframework.data.domain.Page;

import com.ciandt.summit.bootcamp2022.model.MusicEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicDto {
    public String name;
    public String id;

    public MusicDto(MusicEntity music) {
        this.name = music.getName();
        this.id = music.getId();
    }

    public static Page<MusicDto> convert(Page<MusicEntity> page) {
        return page.map(MusicDto::new);
    }

}
