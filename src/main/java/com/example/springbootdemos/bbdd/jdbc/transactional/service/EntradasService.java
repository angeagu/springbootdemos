package com.example.springbootdemos.bbdd.jdbc.transactional.service;

import com.example.springbootdemos.bbdd.jdbc.transactional.model.Entrada;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EntradasService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void add(Entrada entrada) {
        log.info("add - " + entrada.toString());
        jdbcTemplate.update("insert into ENTRADAS values (?, ?, ?, ?)", entrada.getId(),
                entrada.getPelicula(), entrada.getPrecio(), entrada.getFecha());
    }

    public List<String> getPeliculas() {
        return jdbcTemplate.query("select pelicula from ENTRADAS",
                (rs, rowNum) -> rs.getString("pelicula"));
    }
}
