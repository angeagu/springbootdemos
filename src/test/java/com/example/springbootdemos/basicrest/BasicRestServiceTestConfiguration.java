package com.example.springbootdemos.basicrest;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicRestServiceTestConfiguration
{

    Date creationDate = new Date();

    @Bean
    public Date creationDate() {
        return creationDate;
    }

    @Bean
    List<EmpleadoDTO> listaEmpleados() {
        List<EmpleadoDTO> listaEmpleados = new ArrayList<>();
        EmpleadoDTO empleado = EmpleadoDTO.builder()
                .fullName("Angel Aguado")
                .creation(creationDate())
                .id("1")
                .build();
        EmpleadoDTO empleado2 = EmpleadoDTO.builder()
                .fullName("Javier Lopez")
                .creation(creationDate())
                .id("2")
                .build();
        listaEmpleados.add(empleado);
        listaEmpleados.add(empleado2);
        return listaEmpleados;
    }
}
