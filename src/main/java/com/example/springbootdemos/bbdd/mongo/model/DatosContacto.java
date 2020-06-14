package com.example.springbootdemos.bbdd.mongo.model;

import lombok.Data;

@Data
public class DatosContacto {

    private String direccion;
    private int numero;
    private int codigoPostal;
    private String telefono;

}
