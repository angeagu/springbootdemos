package com.example.springbootdemos.rest.feign;

import com.example.springbootdemos.rest.feignclient.FeignRestController;
import com.example.springbootdemos.rest.feignclient.PostClient;
import com.example.springbootdemos.rest.feignclient.PostData;
import com.example.springbootdemos.rest.simpleclient.JsonPlaceholderController;
import com.example.springbootdemos.rest.simpleclient.JsonPlaceholderControllerTestConfiguration;
import com.example.springbootdemos.rest.simpleclient.JsonPlaceholderService;
import com.example.springbootdemos.rest.simpleclient.TodoData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= {FeignRestControllerTestConfiguration.class, FeignRestController.class}, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FeignRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostClient postClient;

    @Test
    void getTodo() throws Exception {/*
        PostData postData = new PostData();
        postData.setId(1);
        postData.setBody("Mocked Body");
        postData.setTitle("Title mocked");
        postData.setUserId(2);

        when(postClient.findById(22)).thenReturn(postData);
        this.mockMvc.perform(get("/demo/rest/feign/jsonplaceholder/posts/22")
                .contentType((MediaType.APPLICATION_JSON))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());*/
    }

}
