package com.example.training;

import com.example.training.controller.TestController;
import com.example.training.rest.simpleclient.JsonPlaceholderController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.example.training.controller.PingController;


@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "com.example.training.controller")
@EnableFeignClients
@Import({ PingController.class, JsonPlaceholderController.class, TestController.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
