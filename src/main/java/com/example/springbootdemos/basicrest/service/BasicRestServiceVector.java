package com.example.springbootdemos.basicrest.service;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import com.example.springbootdemos.basicrest.exception.EmpleadoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Profile("vector")
@Service
@AllArgsConstructor
public class BasicRestServiceVector implements IBasicRestService {

    private final Vector<EmpleadoDTO> empleadoDTOVector;

    public void addEmpleado(EmpleadoDTO e) {
        empleadoDTOVector.add(e);
    }

    public List<EmpleadoDTO> getEmpleados() {
        return empleadoDTOVector;
    }

    public EmpleadoDTO getEmpleado(String id) {
        return empleadoDTOVector.stream().filter(empleado -> empleado.getId().equals(id)).findAny()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }

    public void updateEmpleado(EmpleadoDTO e) {
        for(EmpleadoDTO empleado : empleadoDTOVector) {
            if (empleado.getId().equals(e.getId())) {
                empleado.setCreation(e.getCreation());
                empleado.setFullName(e.getFullName());
                return;
            }
        }
    }

    public void patchEmpleado(String id, EmpleadoDTO e) {
        for(EmpleadoDTO empleado : empleadoDTOVector) {
            if (empleado.getId().equals(id)) {
                if(e.getFullName()!=null) {
                    empleado.setFullName(e.getFullName());
                }
                if (e.getCreation()!=null) {
                    empleado.setCreation(e.getCreation());
                }
                return;
            }
        }
    }

    public void deleteEmpleado(String id) {
        empleadoDTOVector.removeIf(empleado -> empleado.getId().equals(id));
    }

}
