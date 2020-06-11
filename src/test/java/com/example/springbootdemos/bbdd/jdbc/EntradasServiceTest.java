package com.example.springbootdemos.bbdd.jdbc;

import com.example.springbootdemos.bbdd.jdbc.transactional.model.Entrada;
import com.example.springbootdemos.bbdd.jdbc.transactional.service.EntradasService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EntradasServiceTestConfiguration.class})
public class EntradasServiceTest {

    @InjectMocks
    private EntradasService entradasService;

    private JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);

    @Resource
    private List<String> listaPeliculas;

    @Test
    public void add() {
        Entrada entrada = new Entrada();
        entrada.setId(1);
        entrada.setFecha(new Date());
        entrada.setPelicula("Million Dollar Baby");
        entrada.setPrecio(4.5);
        entradasService.add(entrada);
        verify(jdbcTemplate).update(anyString(), anyLong(),
                anyString(), anyDouble(), any(Date.class));
    }

    @Test
    public void getPeliculas() {
        when(entradasService.getPeliculas()).thenReturn(listaPeliculas);
        List<String> peliculas = entradasService.getPeliculas();
        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
        assert(peliculas.equals(listaPeliculas));
    }

}
