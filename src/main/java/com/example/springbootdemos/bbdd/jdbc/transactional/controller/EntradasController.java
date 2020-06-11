package com.example.springbootdemos.bbdd.jdbc.transactional.controller;

import com.example.springbootdemos.bbdd.jdbc.transactional.service.EntradasService;
import com.example.springbootdemos.bbdd.jdbc.transactional.model.Entrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/demo/bbdd/jdbc/transactional/entradas")
public class EntradasController {

    @Autowired
    EntradasService entradasService;

    @Transactional
    @PostMapping("/add")
    public @ResponseBody ResponseEntity addEntrada(@RequestBody @Valid Entrada entrada) {
        entradasService.add(entrada);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/peliculas")
    public @ResponseBody ResponseEntity getPeliculas() {
        return ResponseEntity.ok(entradasService.getPeliculas());
    }
}