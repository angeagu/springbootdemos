package com.example.springbootdemos.bbdd.jdbc.transactional.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class Entrada implements Serializable {

    @NotNull
    private long id;
    @NotNull
    @Size(min = 2, max = 2000)
    private String pelicula;
    @Min(2)
    private double precio;
    private Date fecha;
}
