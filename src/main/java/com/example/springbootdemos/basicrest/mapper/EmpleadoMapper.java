package com.example.springbootdemos.basicrest.mapper;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import com.example.springbootdemos.basicrest.request.Empleado;

import java.util.Date;

public class EmpleadoMapper {

    public static EmpleadoDTO toEmpleadoDTO(Empleado empleado) {
        return EmpleadoDTO.builder().id(empleado.getId())
                .fullName(empleado.getNombre() + " " + empleado.getApellido())
                .creation(new Date())
                .build();
    }
}
