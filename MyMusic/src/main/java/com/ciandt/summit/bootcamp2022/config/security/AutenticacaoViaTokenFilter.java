package com.ciandt.summit.bootcamp2022.config.security;

import com.ciandt.summit.bootcamp2022.http.TokenClient;
import com.ciandt.summit.bootcamp2022.http.dto.CreateAuthorizerRequestData;
import com.ciandt.summit.bootcamp2022.http.dto.CreateTokenRequestDataDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenAuthorizerRequestDto;
import com.ciandt.summit.bootcamp2022.http.dto.TokenRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private final TokenClient tokenClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username1 = request.getHeader("Username");

        if (username1 == null)
            throw new RuntimeException("Sem header");


        TokenRequestDto username = TokenRequestDto.builder()
                .data(CreateTokenRequestDataDto.builder().name(username1).build())
                .build();
        String token = tokenClient.getToken(username).getBody();

        if (token != null){
            autenticarCliente(token, username1);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token, String username) {

        TokenAuthorizerRequestDto authorization = TokenAuthorizerRequestDto.builder()
                .data(CreateAuthorizerRequestData.builder().name(username).token(token).build())
                        .build();

        String returnedAuthorization = tokenClient.getAuthorization(authorization).getBody();

        if (!returnedAuthorization.equals("ok"))
            throw new RuntimeException("Sem autorizacao");

    }
}
