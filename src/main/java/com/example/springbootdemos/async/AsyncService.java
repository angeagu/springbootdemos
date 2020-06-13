package com.example.springbootdemos.async;

import com.example.springbootdemos.rest.simpleclient.model.TodoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    private RestTemplate restTemplate;

    @Autowired
    public AsyncService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Async //Indica que debe ejecutarse en un thread aparte.
    public CompletableFuture<TodoData> getTodo(int id) throws InterruptedException{
        TodoData todoData = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/"+id, TodoData.class);
        Thread.sleep(1000); //Simulamos un retardo artificial
        //En lugar de TodoData, devolvemos un CompletableFuture<TodoData>
        return CompletableFuture.completedFuture(todoData);
    }

}


