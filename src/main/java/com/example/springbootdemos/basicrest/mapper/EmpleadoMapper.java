package com.example.springbootdemos.basicrest.mapper;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import com.example.springbootdemos.basicrest.request.Empleado;

public class EmpleadoMapper {

    public static EmpleadoDTO toEmpleadoDTO(Empleado empleado) {
        return EmpleadoDTO.builder().id(empleado.getId())
                .name(empleado.getNombre())
                .surname(empleado.getApellido())
                .position(empleado.getCargo()).build();
    }
}
