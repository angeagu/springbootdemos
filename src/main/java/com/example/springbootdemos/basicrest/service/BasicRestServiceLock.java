package com.example.springbootdemos.basicrest.service;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import com.example.springbootdemos.basicrest.exception.EmpleadoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("lock")
@Service
@AllArgsConstructor
public class BasicRestServiceLock implements IBasicRestService {

    private final List<EmpleadoDTO> empleadoDTOList;

    public void addEmpleado(EmpleadoDTO e) {
        empleadoDTOList.add(e);
    }

    public List<EmpleadoDTO> getEmpleados() {
        return empleadoDTOList;
    }

    public EmpleadoDTO getEmpleado(String id) {
        return empleadoDTOList.stream().filter(empleado -> empleado.getId().equals(id)).findAny()
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }

    public synchronized void updateEmpleado(EmpleadoDTO e) {
        for(EmpleadoDTO empleado : empleadoDTOList) {
            if (empleado.getId().equals(e.getId())) {
                empleado.setCreation(e.getCreation());
                empleado.setFullName(e.getFullName());
                try {
                    if (e.getFullName().contains("Torres")) {
                        Thread.sleep(10000);
                    }
                }
                catch (Exception ex) {

                }
                return;
            }
        }
    }

    public synchronized void patchEmpleado(String id, EmpleadoDTO e) {
        for(EmpleadoDTO empleado : empleadoDTOList) {
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

    public synchronized void deleteEmpleado(String id) {
        empleadoDTOList.removeIf(empleado -> empleado.getId().equals(id));
    }

}
