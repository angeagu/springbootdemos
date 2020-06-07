package com.example.springbootdemos.bbdd.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "alumnos")
public class Alumno {

    @Id
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;

}
