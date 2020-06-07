package com.example.springbootdemos.bbdd.mongo.springdata.controller;

import com.example.springbootdemos.bbdd.mongo.model.Alumno;
import com.example.springbootdemos.bbdd.mongo.springdata.service.AlumnoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/demo/bbdd/mongo")
@AllArgsConstructor
public class AlumnoController {
    
    @Autowired
    private final AlumnoService alumnoService;

    @GetMapping(value="/alumno", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Alumno>> getAlumnos() {
        return ResponseEntity.ok().body(alumnoService.getAlumnos());
    }

    @GetMapping(value="/alumno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> getAlumno(@PathVariable(required=true) Integer id) {
        return ResponseEntity.ok().body(alumnoService.getAlumno(id));
    }

    @PostMapping(value="/alumno", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAlumno(@RequestBody @Valid Alumno e) {
        alumnoService.addAlumno(e);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/alumno", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> updateAlumno(@RequestBody @Valid Alumno e) {
        alumnoService.updateAlumno(e);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/alumno/{id}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable(required = true) Integer id) {
        alumnoService.deleteAlumno(id);
        return ResponseEntity.ok().build();
    }
    
}
