package com.example.springbootdemos.rest.simpleclient;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonPlaceholderService {
	
	private RestTemplate restTemplate;
	 
	@Autowired
	public JsonPlaceholderService(RestTemplateBuilder builder) {
	    this.restTemplate = builder.build();
	}
	
	public TodoData getTodo(int id) {
		return restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/"+id, TodoData.class);

	}
}
