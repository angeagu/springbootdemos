package com.example.springbootdemos.bbdd.mongo.springdata.service;

import com.example.springbootdemos.bbdd.mongo.model.Alumno;
import com.example.springbootdemos.bbdd.mongo.repository.AlumnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public void addAlumno(Alumno alumno) {
        alumnoRepository.save(alumno);
    }

    public List<Alumno> getAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno getAlumno(Integer id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    public void updateAlumno(Alumno alumno) {
        Alumno al = alumnoRepository.findById(alumno.getId()).orElse(null);
        if(al!=null) {
            al.setNombre(alumno.getNombre());
            al.setApellido(alumno.getApellido());
            al.setDireccion(alumno.getDireccion());
        }
        alumnoRepository.save(al);
    }

    public void deleteAlumno(Integer id) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if(alumno!=null) {
            alumnoRepository.delete(alumno);
        }
    }

}
