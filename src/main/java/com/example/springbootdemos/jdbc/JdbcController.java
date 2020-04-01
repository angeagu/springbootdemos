package com.example.springbootdemos.jdbc;

import java.sql.SQLException;
import java.util.Arrays;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/demo/jdbc/productos")
public class JdbcController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@GetMapping(path="/select", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity selectProductos(@RequestParam(required=false) Integer id_producto) {	
		if(id_producto!=null) {
			Integer[] array = {id_producto};
			return ResponseEntity.ok(productoRepository.findAllById(Arrays.asList(array)));
		}
	    return ResponseEntity.ok(productoRepository.findAll());
	}
	
	@PostMapping(path="/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity insertProducto(@RequestBody(required=true) Producto producto) {
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
	
	@DeleteMapping(path="/delete/{id_producto}") //id producto as PathVariable
	public @ResponseBody ResponseEntity deleteProducto(@PathVariable(required=true) Integer id_producto) {
		productoRepository.deleteById(id_producto);
		return ResponseEntity.ok().build();
	}
}
