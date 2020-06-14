package com.example.springbootdemos.basicrest;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class BasicRestServiceTestConfiguration
{
    @Bean
    List<EmpleadoDTO> listaEmpleados() {
        List<EmpleadoDTO> listaEmpleados = new ArrayList<>();
        EmpleadoDTO empleado = EmpleadoDTO.builder()
                .name("Angel")
                .surname("Aguado")
                .position("Tech Lead")
                .id("1")
                .build();
        EmpleadoDTO empleado2 = EmpleadoDTO.builder()
                .name("Javier")
                .surname("Lopez")
                .position("Ingeniero Software")
                .id("2")
                .build();
        listaEmpleados.add(empleado);
        listaEmpleados.add(empleado2);
        return listaEmpleados;
    }
}
