package com.example.springbootdemos.rest.feign;

import com.example.springbootdemos.rest.feignclient.PostClient;
import com.example.springbootdemos.rest.feignclient.PostData;
import com.example.springbootdemos.rest.shared.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FeignRestControllerTest extends BaseControllerTest {

    @MockBean
    private PostClient postClient;

    @Test
    void getTodo() throws Exception {
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
                .andExpect(status().isOk());
    }

}
