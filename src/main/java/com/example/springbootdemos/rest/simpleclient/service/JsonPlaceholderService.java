package com.example.springbootdemos.rest.simpleclient.service;

import com.example.springbootdemos.rest.simpleclient.model.TodoData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class JsonPlaceholderService {

	private RestTemplate restTemplate;
	 
	public JsonPlaceholderService(RestTemplateBuilder builder) {
	    this.restTemplate = builder.build();
	}
	
	public TodoData getTodo(int id) {
		return restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/"+id, TodoData.class);
	}

	//@Cacheable("todos") Permite cachear datos, si van a tardar en ser
	//obtenidos. Requiere @EnableCaching en la @SpringBootApplication
	public List<TodoData> getTodos() {
		/*try {
			Thread.sleep(3000);
		}
		catch (InterruptedException ex) {
		}*/
		return restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/", List.class);
	}

	public String addTodo(TodoData todoData) {
		HttpEntity<String> request =
				new HttpEntity<>(todoData.toString());
		return restTemplate.postForObject("https://jsonplaceholder.typicode.com/todos", request, String.class);
	}

	public void updateTodo(Integer id, TodoData todoData) {
		HttpEntity<String> request =
				new HttpEntity<>(todoData.toString());
		restTemplate.put("https://jsonplaceholder.typicode.com/todos/"+id, request);
	}
}
