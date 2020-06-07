package com.example.springbootdemos.rest.feignclient.controller;

import java.util.stream.Collectors;

import com.example.springbootdemos.rest.feignclient.client.PostClient;
import com.example.springbootdemos.rest.feignclient.model.PostData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/demo/rest/feign/jsonplaceholder")
public class FeignRestController {


	@Autowired
	private PostClient postClient;

	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getPosts(@RequestParam(required=false) Integer userId) {
		if(userId!=null) {
			return ResponseEntity.ok(postClient.findByUserId(userId));
		}
		return ResponseEntity.ok(postClient.findAll().stream().collect(Collectors.toList()));
	}
	
	@GetMapping(path="/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getPost(@PathVariable(required=true) Integer id) {
		return ResponseEntity.ok(postClient.findById(id));
	}
	
	@PostMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity addPost(@RequestBody @Validated PostData postData) {
		return ResponseEntity.ok().build();
	}
	
}
