package com.ciandt.summit.bootcamp2022.http;

import com.ciandt.summit.bootcamp2022.exceptions.AuthorizedHandler;
import com.ciandt.summit.bootcamp2022.http.dto.CreateAuthorizerRequestData;
import com.ciandt.summit.bootcamp2022.http.dto.CreateTokenRequestDataDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenAuthorizerRequestDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenAuthorizedClientUtils {
    private final TokenAuthorizedClient tokenAuthorizedClient;

    public void isAuthorized(String username) {

        try{

            TokenRequestDto userName = TokenRequestDto.builder()
                    .data(CreateTokenRequestDataDto.builder().name(username).build())
                    .build();
            String token = tokenAuthorizedClient.getToken(userName).getBody();

            TokenAuthorizerRequestDto authorization = TokenAuthorizerRequestDto.builder()
                    .data(CreateAuthorizerRequestData.builder().name(username).token(token).build())
                    .build();

            tokenAuthorizedClient.getAuthorization(authorization);

        } catch (RuntimeException err){
            throw new AuthorizedHandler.TokenServiceException();
        }
    }
}
