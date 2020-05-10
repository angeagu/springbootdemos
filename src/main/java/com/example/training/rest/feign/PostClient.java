package com.example.training.rest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value="postClient", url="https://jsonplaceholder.typicode.com")
public interface PostClient {

	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	List<PostData> findAll();
	
	@GetMapping(path="/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	PostData findById(@PathVariable("id") int id);
	
	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	PostData findByUserId(@RequestParam(required = true) Integer userId);
	
	@PostMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> create(@RequestBody @Validated PostData postsData);
}
