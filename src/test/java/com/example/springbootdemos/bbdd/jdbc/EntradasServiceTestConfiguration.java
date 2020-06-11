package com.example.springbootdemos.bbdd.jdbc;

import com.example.springbootdemos.basicrest.model.Empleado;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class EntradasServiceTestConfiguration {

    @Bean
    List<String> listaPeliculas() {
        List<String> listaPeliculas = new ArrayList<>();
        listaPeliculas.add("Cars 3");
        listaPeliculas.add("Matrix");
        listaPeliculas.add("Black Widow");
        listaPeliculas.add("La lista de Schindler");
        listaPeliculas.add("Goodbye Lenin");
        return listaPeliculas;
    }
}
