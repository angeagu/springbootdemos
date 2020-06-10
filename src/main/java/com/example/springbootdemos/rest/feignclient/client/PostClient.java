package com.example.springbootdemos.rest.feignclient.client;

import java.util.List;

import com.example.springbootdemos.rest.feignclient.model.PostData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(value="postClient", url="https://jsonplaceholder.typicode.com")
public interface PostClient {

	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PostData> findAll();
	
	@GetMapping(path="/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostData findById(@PathVariable("id") int id);
	
	@GetMapping(path="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostData findByUserId(@RequestParam(required=true) Integer userId);
	
	@PostMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody @Valid PostData postsData);
}
