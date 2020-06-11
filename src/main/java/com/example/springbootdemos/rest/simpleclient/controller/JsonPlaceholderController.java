package com.example.springbootdemos.rest.simpleclient.controller;

import com.example.springbootdemos.rest.simpleclient.service.JsonPlaceholderService;
import com.example.springbootdemos.rest.simpleclient.model.TodoData;
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
	public @ResponseBody TodoData getTodo(@PathVariable(required=true) Integer id) {
		return jsonPlaceholderService.getTodo(id);
	}

	@GetMapping(path="/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TodoData> getTodos() {
		return jsonPlaceholderService.getTodos();
	}

	@PostMapping(path="/todos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity addTodo(@RequestBody @Valid TodoData todoData) {
		return ResponseEntity.ok().body(jsonPlaceholderService.addTodo(todoData));
	}

	@PutMapping(path="/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity updateTodo(@PathVariable(required=true) Integer id, @RequestBody TodoData todoData) {
		jsonPlaceholderService.updateTodo(id,todoData);
		return ResponseEntity.ok().body("OK");
	}

}
