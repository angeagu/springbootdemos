package com.example.springbootdemos.restdocs.rest.simpleclient;

import com.example.springbootdemos.rest.simpleclient.model.TodoData;
import com.example.springbootdemos.rest.simpleclient.service.JsonPlaceholderService;
import com.example.springbootdemos.restdocs.rest.BaseControllerTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(outputDir = "target/snippets")
public class JsonPlaceholderControllerTest extends BaseControllerTest {

	@MockBean
	private JsonPlaceholderService jsonPlaceholderService;

	@Test
	void getTodos() throws Exception {
		List<TodoData> todoDataList = new ArrayList<>();
		TodoData todoData = new TodoData();
		todoData.setCompleted(true);
		todoData.setId(1);
		todoData.setTitle("Title mocked");
		todoData.setUserId(2);
		TodoData todoData2 = new TodoData();
		todoData2.setCompleted(false);
		todoData2.setId(2);
		todoData2.setTitle("Title mocked 2");
		todoData2.setUserId(1);
		todoDataList.add(todoData);
		todoDataList.add(todoData2);

		when(jsonPlaceholderService.getTodos()).thenReturn(todoDataList);
		this.mockMvc.perform(get("/demo/rest/consumer/jsonplaceholder/todos")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andDo(MockMvcRestDocumentation.document("simpleclient/jsonplaceholder/todos",
						responseFields(
								fieldWithPath("[].userId").description("Todo User ID"),
								fieldWithPath("[].id").description("Todo ID"),
								fieldWithPath("[].title").description("Todo Title"),
								fieldWithPath("[].completed").description("Todo Status (true=completed)")
							)));
	}

    @Test
    void getTodo() throws Exception {
    	TodoData todoData = new TodoData();
    	todoData.setCompleted(true);
    	todoData.setId(22);
    	todoData.setTitle("Title mocked");
    	todoData.setUserId(2);
    	
    	when(jsonPlaceholderService.getTodo(22)).thenReturn(todoData);
    	this.mockMvc.perform(get("/demo/rest/consumer/jsonplaceholder/todos/22")
    			.accept(MediaType.APPLICATION_JSON_VALUE))
    			.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("simpleclient/jsonplaceholder/todos",
						responseFields(
								fieldWithPath("completed").description("Todo Status (true=completed)"),
								fieldWithPath("id").description("Todo ID"),
								fieldWithPath("title").description("Todo Title"),
								fieldWithPath("userId").description("Todo User ID")
						)));
    }

	@Test
	void addTodo() throws Exception {
		TodoData todoData = new TodoData();
		todoData.setCompleted(true);
		todoData.setId(1);
		todoData.setTitle("Title mocked");
		todoData.setUserId(2);

		ObjectMapper objectMapper = new ObjectMapper();
		String content = objectMapper.writeValueAsString(todoData);
		when(jsonPlaceholderService.addTodo(any(TodoData.class))).thenReturn("{\n" +
				"  \"id\" : 1\n" + "}");

		this.mockMvc.perform(post("/demo/rest/consumer/jsonplaceholder/todos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andDo(MockMvcRestDocumentation.document("simpleclient/jsonplaceholder/todos",
						requestFields(
							fieldWithPath("id").description("Todo ID"),
							fieldWithPath("userId").description("Todo User ID"),
							fieldWithPath("title").description("Todo Title"),
							fieldWithPath("completed").description("Todo Status (true=completed)")
						),
						responseFields(
								fieldWithPath("id").description("Todo ID")
						)));
	}

	@Test
	void updateTodo() throws Exception {
		TodoData todoData = new TodoData();
		todoData.setCompleted(false);
		todoData.setId(1);
		todoData.setTitle("Title mocked 2");
		todoData.setUserId(2);

		ObjectMapper objectMapper = new ObjectMapper();
		String content = objectMapper.writeValueAsString(todoData);

		this.mockMvc.perform(put("/demo/rest/consumer/jsonplaceholder/todos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andDo(MockMvcRestDocumentation.document("simpleclient/jsonplaceholder/todos",
						requestFields(
							fieldWithPath("id").description("Todo ID"),
							fieldWithPath("userId").description("Todo User ID"),
							fieldWithPath("title").description("Todo Title"),
							fieldWithPath("completed").description("Todo Status (true=completed)")
						)));
	}
}
