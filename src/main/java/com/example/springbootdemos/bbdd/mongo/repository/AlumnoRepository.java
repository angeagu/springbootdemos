package com.example.springbootdemos.bbdd.mongo.repository;

import com.example.springbootdemos.bbdd.mongo.model.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "alumnos", path = "alumnos")
public interface AlumnoRepository extends MongoRepository<Alumno, Integer> {

    List<Alumno> findByNombreContains(@Param("nombre") String nombre);
    List<Alumno> findByApellidoContains(@Param("apellido") String apellido);
    List<Alumno> findByDireccionContains(@Param("direccion") String direccion);

}
