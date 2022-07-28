//package com.ciandt.summit.bootcamp2022.config.security;
//
//
//import com.ciandt.summit.bootcamp2022.http.TokenClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Configuration
//public class SecurityConfiguration {
//
//    @Autowired
//    private TokenClient tokenClient;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//
//        http.authorizeHttpRequests(authorizedRequests -> authorizedRequests
//                        .antMatchers(HttpMethod.POST, "/**").permitAll()
//                        .antMatchers("/swagger-ui/**").permitAll()
//                        .antMatchers("/v3/api-docs/**").permitAll()
//                        .antMatchers(HttpMethod.POST, "/api/v1/token/authorizer").permitAll()
//                        .anyRequest().authenticated())
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().addFilter(new AutenticacaoViaTokenFilter(tokenClient));
//        return http.build();
//    }
//}
