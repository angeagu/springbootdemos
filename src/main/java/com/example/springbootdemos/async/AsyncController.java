package com.example.springbootdemos.async;

import com.example.springbootdemos.rest.simpleclient.model.TodoData;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@RequestMapping(path="/demo/async/jsonplaceholder")
public class AsyncController {

    private AsyncService asyncService;

    @GetMapping(path="/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoData> getTodo(@PathVariable(required=true) Integer id) throws InterruptedException, ExecutionException {
        return ResponseEntity.ok(asyncService.getTodo(id).get());
    }

}
