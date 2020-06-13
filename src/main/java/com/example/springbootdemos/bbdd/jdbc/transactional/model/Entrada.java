package com.example.springbootdemos.bbdd.jdbc.transactional.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
