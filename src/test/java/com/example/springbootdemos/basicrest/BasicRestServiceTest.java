package com.example.springbootdemos.basicrest;

import com.example.springbootdemos.basicrest.dto.EmpleadoDTO;
import com.example.springbootdemos.basicrest.exception.EmpleadoNoEncontradoException;
import com.example.springbootdemos.basicrest.service.BasicRestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BasicRestServiceTestConfiguration.class})
public class BasicRestServiceTest {

    private BasicRestService basicRestService = new BasicRestService(new ArrayList());

    @Resource
    private List<EmpleadoDTO> listaEmpleados;

    @Before
    public void init() {
        log.info("init, before each...");
        EmpleadoDTO empleado = EmpleadoDTO.builder()
                .name("Angel")
                .surname("Aguado")
                .position("Tech Lead")
                .id("1")
                .build();
        EmpleadoDTO empleado2 = EmpleadoDTO.builder()
                .name("Javier")
                .surname("Lopez")
                .position("Ingeniero Software")
                .id("2")
                .build();
        basicRestService.addEmpleado(empleado);
        basicRestService.addEmpleado(empleado2);
    }

    @Test
    public void getEmpleados() {
        assert basicRestService.getEmpleados().size() == listaEmpleados.size();
        assert basicRestService.getEmpleados().get(0).equals(listaEmpleados.get(0));
        assert basicRestService.getEmpleados().get(1).equals(listaEmpleados.get(1));
    }

    @Test
    public void getEmpleado() {
        assert (basicRestService.getEmpleado("1").equals(listaEmpleados.get(0)));
    }

    @Test
    public void addEmpleado() {
        EmpleadoDTO empleado = EmpleadoDTO.builder()
                .name("Pedro")
                .surname("Perez")
                .position("Tester")
                .id("3")
                .build();
        basicRestService.addEmpleado(empleado);
        assert (basicRestService.getEmpleados().size() == 3);
    }

    @Test
    public void updateEmpleado() {
        EmpleadoDTO empleado = EmpleadoDTO.builder()
                .name("Javier")
                .surname("Lopez")
                .position("Analista Funcional")
                .id("2")
                .build();
        basicRestService.updateEmpleado(empleado);
        assert (basicRestService.getEmpleado("2").getPosition().equals("Analista Funcional"));
    }

    @Test
    public void deleteEmpleado() {
        basicRestService.deleteEmpleado("1");
        Assertions.assertThrows(EmpleadoNoEncontradoException.class, () -> basicRestService.getEmpleado("1"));
        assert (basicRestService.getEmpleados().size() == 1);
    }


}
