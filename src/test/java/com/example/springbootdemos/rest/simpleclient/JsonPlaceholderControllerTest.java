package com.example.springbootdemos.rest.simpleclient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(classes= {JsonPlaceholderControllerTestConfiguration.class, JsonPlaceholderController.class}, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class JsonPlaceholderControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
    
	@MockBean
	private JsonPlaceholderService jsonPlaceholderService;
	
    @Test
    void getTodo() throws Exception {
    	TodoData todoData = new TodoData();
    	todoData.setCompleted(true);
    	todoData.setId(1);
    	todoData.setTitle("Title mocked");
    	todoData.setUserId(2);
    	
    	when(jsonPlaceholderService.getTodo(22)).thenReturn(todoData);
    	this.mockMvc.perform(get("/demo/rest/consumer/jsonplaceholder/todo/22")
    			.accept(MediaType.APPLICATION_JSON_UTF8))
    			.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    
}
