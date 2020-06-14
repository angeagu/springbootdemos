package com.example.springbootdemos.basicrest;

import com.example.springbootdemos.basicrest.request.Empleado;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class BasicRestServiceTestConfiguration
{
    @Bean
    List<Empleado> listaEmpleados() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Empleado empleado = new Empleado();
        empleado.setNombre("Angel");
        empleado.setCargo("Tech Lead");
        empleado.setApellido("Aguado");
        empleado.setId("1");
        listaEmpleados.add(empleado);
        Empleado empleado2 = new Empleado();
        empleado2.setNombre("Javier");
        empleado2.setCargo("Ingeniero Software");
        empleado2.setApellido("Lopez");
        empleado2.setId("2");
        listaEmpleados.add(empleado2);
        return listaEmpleados;
    }
}
