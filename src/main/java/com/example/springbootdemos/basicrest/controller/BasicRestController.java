package com.example.springbootdemos.basicrest.controller;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import com.example.springbootdemos.basicrest.mapper.EmpleadoMapper;
import com.example.springbootdemos.basicrest.request.Empleado;
import com.example.springbootdemos.basicrest.service.BasicRestService;
import com.example.springbootdemos.basicrest.service.IBasicRestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/demo/basicrest")
@AllArgsConstructor
public class BasicRestController {

    @Autowired
    private final IBasicRestService basicRestService;

    @GetMapping(value="/empleados", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmpleadoDTO>> getEmpleados() {
        return ResponseEntity.ok().body(basicRestService.getEmpleados());
    }

    @GetMapping(value="/empleado/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmpleadoDTO> getEmpleado(@PathVariable(required=true) String id) {
        return ResponseEntity.ok().body(basicRestService.getEmpleado(id));
    }

    @PostMapping(value="/empleado", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addEmpleado(@RequestBody @Valid Empleado e) {
        basicRestService.addEmpleado(EmpleadoMapper.toEmpleadoDTO(e));
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/empleado", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@RequestBody @Valid Empleado e) {
        basicRestService.updateEmpleado(EmpleadoMapper.toEmpleadoDTO(e));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/empleado/{id}")
    public ResponseEntity<Empleado> deleteEmpleado(@PathVariable(required = true) String id) {
        basicRestService.deleteEmpleado(id);
        return ResponseEntity.ok().build();
    }

}
