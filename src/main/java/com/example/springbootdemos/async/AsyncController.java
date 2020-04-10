package com.example.springbootdemos.async;

import com.example.springbootdemos.rest.simpleclient.TodoData;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@RequestMapping(path="/demo/async/jsonplaceholder")
public class AsyncController {

    private AsyncService asyncService;

    @GetMapping(path="/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    TodoData getTodo(@PathVariable(required=true) Integer id) throws InterruptedException, ExecutionException {
        return asyncService.getTodo(id).get();
    }

}
