package com.ciandt.summit.bootcamp2022.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Artist {
    private String id;

    private String name;

    public Artist(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

}
