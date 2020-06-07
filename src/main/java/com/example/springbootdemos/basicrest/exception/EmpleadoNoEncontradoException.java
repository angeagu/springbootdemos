package com.example.springbootdemos.basicrest.exception;

public class EmpleadoNoEncontradoException extends RuntimeException{

    public EmpleadoNoEncontradoException(String id) {
        super("No se encuentra el empleado " + id);
    }
}
