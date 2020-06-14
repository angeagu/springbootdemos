package com.example.springbootdemos.bbdd.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "colegio")
@Data
public class Colegio {

    @Id
    private String codigo;
    private String nombre;
    private String localidad;

}
