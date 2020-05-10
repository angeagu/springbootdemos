package com.example.training.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class TestController {

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public @ResponseBody TestData test() {
        TestData testData = new TestData();
        testData.setCompleted(true);
        testData.setDescription("Task 1");
        testData.setId(3);
        return testData;
    }
}
