package com.cshop.cosmeticshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CosmeticShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosmeticShopApplication.class, args);
    }

    @Bean
    ObjectMapper getMapper() {
        return new ObjectMapper().findAndRegisterModules();
    }



}
