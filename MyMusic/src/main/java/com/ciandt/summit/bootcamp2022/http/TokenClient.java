package com.ciandt.summit.bootcamp2022.http;

import com.ciandt.summit.bootcamp2022.http.dto.TokenAuthorizerRequestDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(url = "${tokenProvider.service.url}", name = "ms-token-provider")
public interface TokenClient {

    @PostMapping(value = "/api/v1/token")
    ResponseEntity<String> getToken(@RequestBody TokenRequestDto token);

    @PostMapping(value = "/api/v1/token/authorizer")
    ResponseEntity<String> getAuthorization(@RequestBody TokenAuthorizerRequestDto tokenCredentials);



}
