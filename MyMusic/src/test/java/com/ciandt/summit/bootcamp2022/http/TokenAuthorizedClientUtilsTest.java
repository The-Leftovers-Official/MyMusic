package com.ciandt.summit.bootcamp2022.http;


import com.ciandt.summit.bootcamp2022.exceptions.AuthorizedHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TokenAuthorizedClientUtilsTest {

    @Mock
    private TokenAuthorizedClient tokenAuthorizedClient;

    @InjectMocks
    private TokenAuthorizedClientUtils utils;


    @Test
    public void shouldReturnTokerServiceError(){
        Mockito.when(tokenAuthorizedClient.getToken(Mockito.any()))
                .thenThrow(new AuthorizedHandler.TokenServiceException());

        try {
            utils.isAuthorized("user");
            Assertions.fail();
        }catch (RuntimeException e){

        }
    }

    @Test
    public void shouldBeAutherizer(){
        Mockito.when(tokenAuthorizedClient.getToken(Mockito.any()))
                .thenReturn(ResponseEntity.ok("123456"));

        Mockito.when(tokenAuthorizedClient.getAuthorization(Mockito.any()))
                .thenReturn(ResponseEntity.ok().build());

        try {
            utils.isAuthorized("user");
        }catch (RuntimeException e){
        }
    }


}