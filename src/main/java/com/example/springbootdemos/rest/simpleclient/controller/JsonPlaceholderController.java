package com.example.springbootdemos.rest.simpleclient.controller;

import com.example.springbootdemos.rest.simpleclient.service.JsonPlaceholderService;
import com.example.springbootdemos.rest.simpleclient.model.TodoData;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path="/demo/rest/consumer/jsonplaceholder")
public class JsonPlaceholderController {
	
	private JsonPlaceholderService jsonPlaceholderService;
	
	@GetMapping(path="/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TodoData> getTodo(@PathVariable(required=true) Integer id) {
		return ResponseEntity.ok(jsonPlaceholderService.getTodo(id));
	}

	@GetMapping(path="/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TodoData>> getTodos() {
		return ResponseEntity.ok(jsonPlaceholderService.getTodos());
	}

	@PostMapping(path="/todos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addTodo(@RequestBody @Valid TodoData todoData) {
		return ResponseEntity.ok(jsonPlaceholderService.addTodo(todoData));
	}

	@PutMapping(path="/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateTodo(@PathVariable(required=true) Integer id, @RequestBody TodoData todoData) {
		jsonPlaceholderService.updateTodo(id,todoData);
		return ResponseEntity.ok().build();
	}

}
