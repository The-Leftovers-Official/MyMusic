package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.http.TokenAuthorizedClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    private final TokenAuthorizedClientUtils tokenAuthorizedClient;

    @GetMapping
    public ResponseEntity<String> get(@RequestHeader("Username") String username) {

        tokenAuthorizedClient.isAuthorized(username);

        return ResponseEntity.ok().build();
    }

}
