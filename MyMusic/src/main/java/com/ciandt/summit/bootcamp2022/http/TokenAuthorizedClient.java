package com.ciandt.summit.bootcamp2022.http;

import com.ciandt.summit.bootcamp2022.http.dto.TokenAuthorizerRequestDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(url = "${tokenProvider.service.url}", name = "ms-token-provider")
public interface TokenAuthorizedClient {

    @PostMapping(value = "/api/v1/token")
    ResponseEntity<String> getToken(@RequestBody TokenRequestDto token);

    @PostMapping(value = "/api/v1/token/authorizer")
    ResponseEntity<String> getAuthorization(@RequestBody TokenAuthorizerRequestDto tokenCredentials);



}
