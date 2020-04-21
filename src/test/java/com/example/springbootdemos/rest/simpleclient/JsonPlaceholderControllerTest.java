package com.example.springbootdemos.rest.simpleclient;

import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springbootdemos.rest.shared.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@AutoConfigureRestDocs(outputDir = "target/snippets")
public class JsonPlaceholderControllerTest extends BaseControllerTest {

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
    			.accept(MediaType.APPLICATION_JSON_VALUE))
    			.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("jsonplaceholder/todo",
						responseFields(
								fieldWithPath("completed").description("Todo Status (true=completed)"),
								fieldWithPath("id").description("Todo ID"),
								fieldWithPath("title").description("Todo Title"),
								fieldWithPath("userId").description("Todo User ID")
						)));
    }
    
}
