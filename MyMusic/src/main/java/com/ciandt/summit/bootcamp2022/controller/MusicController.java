package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.exceptions.AuthorizedHandler;
import com.ciandt.summit.bootcamp2022.http.TokenClient;
import com.ciandt.summit.bootcamp2022.http.dto.CreateAuthorizerRequestData;
import com.ciandt.summit.bootcamp2022.http.dto.CreateTokenRequestDataDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenAuthorizerRequestDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenRequestDto;
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

    private final TokenClient tokenClient;

    @GetMapping
    public ResponseEntity<String> get(@RequestHeader("Username") String username) {

        if (username == "")
            throw new AuthorizedHandler.InvalidRequestHeaderException();

        Boolean authorized = isAuthorized(username);

        return ResponseEntity.ok().build();
    }

    private Boolean isAuthorized(String username) {

        TokenRequestDto userName = TokenRequestDto.builder()
                .data(CreateTokenRequestDataDto.builder().name(username).build())
                .build();
        String token = tokenClient.getToken(userName).getBody();

        TokenAuthorizerRequestDto authorization = TokenAuthorizerRequestDto.builder()
                .data(CreateAuthorizerRequestData.builder().name(username).token(token).build())
                        .build();

        String returnedAuthorization = tokenClient.getAuthorization(authorization).getBody();

        if (!returnedAuthorization.equals("ok"))
            throw new IllegalArgumentException("Not authorized!");

        return true;
    }
}
