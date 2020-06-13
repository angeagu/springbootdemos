package com.example.springbootdemos.rest.feignclient.client;

import java.util.List;

import com.example.springbootdemos.rest.feignclient.model.PostData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value="postClient", url="https://jsonplaceholder.typicode.com")
public interface PostClient {

	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PostData> findAll();
	
	@GetMapping(path="/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostData findById(@PathVariable("id") int id);
	
	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PostData> findByUserId(@RequestParam(required=true) Integer userId);
	
	@PostMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody @Valid PostData postsData);

	@PutMapping(path="/posts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PostData update(@PathVariable("id") int id, @RequestBody @Valid PostData postsData);

}
