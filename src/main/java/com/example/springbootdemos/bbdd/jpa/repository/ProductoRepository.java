package com.example.springbootdemos.bbdd.jpa.repository;

import com.example.springbootdemos.bbdd.jpa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByStockIsGreaterThan(String cantidad);
}
