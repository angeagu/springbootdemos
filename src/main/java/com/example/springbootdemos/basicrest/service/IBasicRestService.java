package com.example.springbootdemos.basicrest.service;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;

import java.util.List;

public interface IBasicRestService {

    List<EmpleadoDTO> getEmpleados();
    EmpleadoDTO getEmpleado(String id);

    void addEmpleado(EmpleadoDTO empleadoDTO);
    void updateEmpleado(EmpleadoDTO empleadoDTO);
    void patchEmpleado(String id, EmpleadoDTO empleadoDTO);
    void deleteEmpleado(String id);

}
