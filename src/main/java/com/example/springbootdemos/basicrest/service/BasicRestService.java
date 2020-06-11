package com.example.springbootdemos.basicrest.service;

import com.example.springbootdemos.basicrest.exception.EmpleadoNoEncontradoException;
import com.example.springbootdemos.basicrest.model.Empleado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BasicRestService {

    private final List<Empleado> listaEmpleados;

    public void addEmpleado(Empleado e) {
        listaEmpleados.add(e);
    }

    public List<Empleado> getEmpleados() {
        return listaEmpleados;
    }

    public Empleado getEmpleado(String id) {
        return listaEmpleados.stream().filter(empleado -> empleado.getId().equals(id)).findAny()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }

    public void updateEmpleado(Empleado e) {
        for(Empleado empleado : listaEmpleados) {
            if (empleado.getId().equals(e.getId())) {
                empleado.setNombre(e.getNombre());
                empleado.setApellido(e.getApellido());
                empleado.setCargo(e.getCargo());
                return;
            }
        }
    }

    public void deleteEmpleado(String id) {
        listaEmpleados.removeIf(empleado -> empleado.getId().equals(id));
    }

}
