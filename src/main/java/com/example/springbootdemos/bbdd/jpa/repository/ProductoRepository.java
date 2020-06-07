package com.example.springbootdemos.bbdd.jpa.repository;

import com.example.springbootdemos.bbdd.jpa.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}
