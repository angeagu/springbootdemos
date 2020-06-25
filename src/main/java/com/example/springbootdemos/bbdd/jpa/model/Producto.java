package com.example.springbootdemos.bbdd.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Producto {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_producto;
	private String descripcion;
	private float precio;
	private int stock;
	private char tipo;
	
}
