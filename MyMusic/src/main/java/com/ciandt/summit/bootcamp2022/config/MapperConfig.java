package com.ciandt.summit.bootcamp2022.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper obterModelMapper() {
        return new ModelMapper();
    }
}
