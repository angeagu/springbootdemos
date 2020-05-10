package com.example.training.rest.simpleclient;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@AllArgsConstructor
@RestController
@EnableWebMvc
public class JsonPlaceholderController {
	
	private JsonPlaceholderService jsonPlaceholderService;
	
	@RequestMapping(path="/demo/rest/consumer/jsonplaceholder/todo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TodoData getTodo(@RequestParam Integer id) {
		return jsonPlaceholderService.getTodo(id);
	}

}
