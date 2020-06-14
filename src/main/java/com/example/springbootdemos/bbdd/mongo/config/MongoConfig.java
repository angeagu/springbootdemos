package com.example.springbootdemos.bbdd.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public AlumnoCascadeSaveListener AlumnoCascadeSaveListener() {
        return new AlumnoCascadeSaveListener();
    }

}
