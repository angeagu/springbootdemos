package com.example.springbootdemos.basicrest;

import com.example.springbootdemos.basicrest.exception.EmpleadoNoEncontradoException;
import com.example.springbootdemos.basicrest.model.Empleado;
import com.example.springbootdemos.basicrest.service.BasicRestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private List<Empleado> listaEmpleados;

    @Before
    public void init() {
        log.info("init, before each...");
        Empleado empleado = new Empleado();
        empleado.setNombre("Angel");
        empleado.setCargo("Tech Lead");
        empleado.setApellido("Aguado");
        empleado.setId("1");
        Empleado empleado2 = new Empleado();
        empleado2.setNombre("Javier");
        empleado2.setCargo("Ingeniero Software");
        empleado2.setApellido("Lopez");
        empleado2.setId("2");
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
        assert(basicRestService.getEmpleado("1").equals(listaEmpleados.get(0)));
    }

    @Test
    public void addEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setId("3");
        empleado.setNombre("Pedro");
        empleado.setApellido("Llorente");
        empleado.setCargo("Tester");
        basicRestService.addEmpleado(empleado);
        assert(basicRestService.getEmpleados().size()==3);
    }

    @Test
    public void updateEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Javier");
        empleado.setCargo("Analista Funcional");
        empleado.setApellido("Lopez");
        empleado.setId("2");
        basicRestService.updateEmpleado(empleado);
        assert(basicRestService.getEmpleado("2").getCargo().equals("Analista Funcional"));
    }

    @Test
    public void deleteEmpleado() {
        basicRestService.deleteEmpleado("1");
        Assertions.assertThrows(EmpleadoNoEncontradoException.class,() -> basicRestService.getEmpleado("1"));
        assert(basicRestService.getEmpleados().size()==1);
    }



}
