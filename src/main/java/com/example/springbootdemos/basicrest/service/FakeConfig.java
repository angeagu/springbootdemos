package com.example.springbootdemos.basicrest.service;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Vector;

@Configuration
public class FakeConfig {
    @Bean
    public Vector<EmpleadoDTO> getVector() {
        return new Vector<>();
    }
}
