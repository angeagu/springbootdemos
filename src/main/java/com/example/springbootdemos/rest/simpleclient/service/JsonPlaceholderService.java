package com.example.springbootdemos.rest.simpleclient.service;

import com.example.springbootdemos.rest.simpleclient.model.TodoData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@CacheConfig(cacheNames = "todos")
public class JsonPlaceholderService {

	private RestTemplate restTemplate;
	 
	public JsonPlaceholderService(RestTemplateBuilder builder) {
	    this.restTemplate = builder.build();
	}

	/*
		Permite cachear datos, si van a tardar en ser
		//obtenidos. Requiere @EnableCaching en la @SpringBootApplication
		El uso de @EnableCaching y @Cacheable hace que se registre automaticamente
        ConcurrentMapCacheManager para la gestion automatica de la cache
        Utiliza concurrent maps por defecto */
	@Cacheable("todos")
	//Se puede especificar una condicion sobre la entrada para cachear y condiciones sobre el resultado
	//@Cacheable("todos", condition="id>10", unless="#result.updated=false")
	public TodoData getTodo(int id) {
		try {
			Thread.sleep(3000);
		}
		catch (InterruptedException ex) {
		}
		return restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/"+id, TodoData.class);
	}

	@CacheEvict(cacheNames = "todos", allEntries = true)
	public List<TodoData> getTodos() {
		return restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/", List.class);
	}

	@CachePut(cacheNames = "todos", key="#todoData.id")
	public TodoData addTodo(TodoData todoData) {
		HttpEntity<String> request =
				new HttpEntity<>(todoData.toString());
		restTemplate.postForObject("https://jsonplaceholder.typicode.com/todos", request, String.class);
		return todoData;
	}

	@CachePut(cacheNames = "todos", key="#todoData.id")
	public TodoData updateTodo(Integer id, TodoData todoData) {
		HttpEntity<String> request =
				new HttpEntity<>(todoData.toString());
		restTemplate.put("https://jsonplaceholder.typicode.com/todos/"+id, request);
		return todoData;
	}
}
