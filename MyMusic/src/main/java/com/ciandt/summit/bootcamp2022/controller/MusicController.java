package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.http.TokenClient;
import com.ciandt.summit.bootcamp2022.http.dto.CreateTokenRequestDataDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    private final TokenClient tokenClient;

    @GetMapping
    public ResponseEntity<String> get() {
        TokenRequestDto token = TokenRequestDto.builder()
                .data(CreateTokenRequestDataDto.builder().name("Fulano").build())
                .build();

        log.info(tokenClient.getToken(token));

        return ResponseEntity.ok("67f5976c-eb1e-404e-8220-2c2a8a23be47");
    }
}
