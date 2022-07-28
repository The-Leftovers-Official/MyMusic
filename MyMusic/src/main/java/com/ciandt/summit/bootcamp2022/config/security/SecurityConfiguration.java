package com.ciandt.summit.bootcamp2022.config.security;


import com.ciandt.summit.bootcamp2022.http.TokenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private TokenClient tokenClient;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests(authorizedRequests -> authorizedRequests
                        .antMatchers(HttpMethod.POST, "/**").permitAll()
                        .antMatchers("/swagger-ui/**").permitAll()
                        .antMatchers("/v3/api-docs/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/v1/token/authorizer").permitAll()
                        .anyRequest().authenticated())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenClient), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
