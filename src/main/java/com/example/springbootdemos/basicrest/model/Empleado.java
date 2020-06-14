package com.example.springbootdemos.basicrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Empleado {

    @Size(min=3,max=3,message = "El id solo puede contener 3 caracteres")
    @NotNull
    private String id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String nombre;
    @NotNull
    @NotBlank
    @NotEmpty
    private String apellido;
    @NotNull
    @NotBlank
    private String cargo;

}
