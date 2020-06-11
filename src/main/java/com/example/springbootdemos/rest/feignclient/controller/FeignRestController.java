package com.example.springbootdemos.rest.feignclient.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.springbootdemos.rest.feignclient.client.PostClient;
import com.example.springbootdemos.rest.feignclient.model.PostData;
import com.example.springbootdemos.rest.simpleclient.model.TodoData;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/demo/rest/feign/jsonplaceholder")
public class FeignRestController {

	@Autowired
	private PostClient postClient;

	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostData>> getPosts(@RequestParam(required=false) Integer userId) {
		if(userId!=null) {
			return ResponseEntity.ok(postClient.findByUserId(userId));
		}
		return ResponseEntity.ok(postClient.findAll().stream().collect(Collectors.toList()));
	}
	
	@GetMapping(path="/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostData> getPost(@PathVariable(required=true) Integer id) {
		return ResponseEntity.ok(postClient.findById(id));
	}
	
	@PostMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addPost(@RequestBody @Valid PostData postData) {
		return ResponseEntity.ok(postClient.create(postData));
	}

	@PutMapping(path="/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateTodo(@PathVariable(required=true) Integer id, @RequestBody @Valid PostData postData) {
		return ResponseEntity.ok(postClient.update(id,postData));
	}
	
}
