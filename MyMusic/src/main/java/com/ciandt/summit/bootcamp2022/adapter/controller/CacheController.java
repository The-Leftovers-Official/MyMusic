package com.ciandt.summit.bootcamp2022.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    @Operation(summary = "url to clear cache")
    @GetMapping("/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM")
    @CacheEvict(value = "listOfMusicByNameOrArtist", allEntries = true)
    public ResponseEntity<?> invalidateMusicCache() {
        return ResponseEntity.ok().build();
    }

}
