package com.ciandt.summit.bootcamp2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class SummitBootcampApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummitBootcampApplication.class, args);
    }

}
