package com.ciandt.summit.bootcamp2022.config.security;


import com.ciandt.summit.bootcamp2022.http.TokenClient;
import com.ciandt.summit.bootcamp2022.http.dto.CreateTokenRequestDataDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenRequestDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenService {

    private TokenClient tokenClient;

    public String getTokenFromTokenClient(HttpServletRequest request){

        TokenRequestDto username = TokenRequestDto.builder()
                .data(CreateTokenRequestDataDto.builder().name(request.getHeader("Username")).build())
                .build();
        return tokenClient.getToken(username).getBody();
    }
}
