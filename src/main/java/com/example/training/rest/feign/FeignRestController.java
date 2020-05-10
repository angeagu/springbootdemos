package com.example.training.rest.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/demo/rest/feign/jsonplaceholder")
public class FeignRestController {

	@Autowired
	private PostClient postClient;

	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
    ResponseEntity getPosts(@RequestParam(required=false) Integer userId) {
		if(userId!=null) {
			return ResponseEntity.ok(postClient.findByUserId(userId));
		}
		return ResponseEntity.ok(postClient.findAll().stream().collect(Collectors.toList()));
	}
	
	@GetMapping(path="/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
    ResponseEntity getPost(@PathVariable(required=true) Integer id) {
		return ResponseEntity.ok(postClient.findById(id));
	}
	
	@PostMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
    ResponseEntity addPost(@RequestBody @Validated PostData postData) {
		return ResponseEntity.ok().build();
	}
	
}
