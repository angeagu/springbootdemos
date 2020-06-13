package com.example.springbootdemos.bbdd.jdbc.transactional.controller;

import com.example.springbootdemos.bbdd.jdbc.transactional.service.EntradasService;
import com.example.springbootdemos.bbdd.jdbc.transactional.model.Entrada;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/demo/bbdd/jdbc")
@AllArgsConstructor
public class JdbcController {

    private final EntradasService entradasService;

    @GetMapping("/entradas")
    public ResponseEntity<List<Entrada>> getEntradas() {
        return ResponseEntity.ok().body(entradasService.getEntradas());
    }

    @PostMapping("/entradas")
    public ResponseEntity addEntrada(@RequestBody @Valid Entrada entrada) {
        entradasService.add(entrada);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/peliculas")
    public ResponseEntity<List<String>> getPeliculas() {
        return ResponseEntity.ok(entradasService.getPeliculas());
    }
}

