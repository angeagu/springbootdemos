package com.example.springbootdemos.rest.simpleclient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path="/demo/rest/consumer/jsonplaceholder")
public class JsonPlaceholderController {
	
	private JsonPlaceholderService jsonPlaceholderService;
	
	@GetMapping(path="/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TodoData getTodo(@PathVariable(required=true) Integer id) {
		return jsonPlaceholderService.getTodo(id);
	}

}
