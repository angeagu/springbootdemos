package com.example.springbootdemos.rest.feignclient.config;

import com.example.springbootdemos.rest.feignclient.client.PostClient;
import feign.Feign;
import feign.Logger;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostClientFeignConfig {

    @Value("${post-client-server}")
    private String postClientServer;

    @Value("${feign-client-log-level}")
    private String logLevel;

    @Bean
    PostClient postClientFeignClient() {
        return Feign.builder()
                .client(new ApacheHttpClient())
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(PostClient.class))
                .logLevel(Logger.Level.valueOf(logLevel))
                .target(PostClient.class, postClientServer);
    }


}
