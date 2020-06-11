package com.example.springbootdemos.basicrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Empleado {

    @Size(min=3,max=3)
    @NotNull
    private String id;
    @NotNull
    private String nombre;
    private String apellido;
    private String cargo;

}
