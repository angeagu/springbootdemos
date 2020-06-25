package com.example.springbootdemos.bbdd.jpa.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.springbootdemos.bbdd.jpa.repository.ProductoRepository;
import com.example.springbootdemos.bbdd.jpa.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/demo/bbdd/jpa/")
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@GetMapping(path="/productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> selectProductos(@RequestParam(required=false) Integer id_producto) {
		if(id_producto!=null) {
			Integer[] array = {id_producto};
			return ResponseEntity.ok().body(productoRepository.findAllById(Arrays.asList(array)));
		}
		return ResponseEntity.ok().body(productoRepository.findAll());
	}
	
	@PostMapping(path="/productos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity insertProducto(@RequestBody(required=true) Producto producto) {
		try {
			productoRepository.save(producto);
			return ResponseEntity.ok().build();
		}
		catch(IllegalArgumentException ex) {
			//Gestion de errores con ResponseStatusException (Spring 5 y superior)
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Los datos JSON del producto son invalidos", ex);
		}
	}
	
	@DeleteMapping(path="/productos/{id_producto}") //id producto as PathVariable
	public ResponseEntity deleteProducto(@PathVariable(required=true) Integer id_producto) {
		productoRepository.deleteById(id_producto);
		return ResponseEntity.ok().build();
	}
}
