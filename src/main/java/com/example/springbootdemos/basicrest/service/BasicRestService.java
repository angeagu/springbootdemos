package com.example.springbootdemos.basicrest.service;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import com.example.springbootdemos.basicrest.exception.EmpleadoNoEncontradoException;
import com.example.springbootdemos.basicrest.request.Empleado;
import com.example.springbootdemos.basicrest.mapper.EmpleadoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BasicRestService implements IBasicRestService{

    private final List<EmpleadoDTO> listaEmpleados;

    public void addEmpleado(EmpleadoDTO e) {
        listaEmpleados.add(e);
    }

    public List<EmpleadoDTO> getEmpleados() {
        return listaEmpleados;
    }

    public EmpleadoDTO getEmpleado(String id) {
        return listaEmpleados.stream().filter(empleado -> empleado.getId().equals(id)).findAny()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }

    public void updateEmpleado(EmpleadoDTO e) {
        for(EmpleadoDTO empleado : listaEmpleados) {
            if (empleado.getId().equals(e.getId())) {
                empleado.setName(e.getName());
                empleado.setSurname(e.getSurname());
                empleado.setPosition(e.getPosition());
                return;
            }
        }
    }

    public void deleteEmpleado(String id) {
        listaEmpleados.removeIf(empleado -> empleado.getId().equals(id));
    }

}
