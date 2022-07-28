//package com.ciandt.summit.bootcamp2022.interceptor;
//
//import com.ciandt.summit.bootcamp2022.annotations.AllowAnnonymous;
//import com.ciandt.summit.bootcamp2022.http.TokenClient;
//import com.ciandt.summit.bootcamp2022.http.dto.CreateAuthorizerRequestData;
//import com.ciandt.summit.bootcamp2022.http.dto.CreateTokenRequestDataDto;
//import com.ciandt.summit.bootcamp2022.http.dto.TokenAuthorizerRequestDto;
//import com.ciandt.summit.bootcamp2022.http.dto.TokenRequestDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class Interceptor implements HandlerInterceptor {
//
//    private final TokenClient tokenClient;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String username = request.getHeader("Username");
//
//        if (username == null) {
//            response.addHeader("Interceptor", "Authorization não enviado");
//            log.info("Username not sent.");
//            return false;
//        }
//
//        TokenRequestDto token = TokenRequestDto.builder()
//                .data(CreateTokenRequestDataDto.builder().name(username).build())
//                .build();
//
//        String returnedToken = tokenClient.getToken(token);
//
//        TokenAuthorizerRequestDto authorization = TokenAuthorizerRequestDto.builder()
//                .data(CreateAuthorizerRequestData.builder().name(username).token(returnedToken).build())
//                        .build();
//
//
//        String returnedAuthorization = tokenClient.getAuthorization(authorization);
//
//        if(!returnedAuthorization.equals("200 ok")) {
//            return false;
//        }
//
//        return false;
//    }
//
//}
