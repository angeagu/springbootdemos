package com.example.springbootdemos.restdocs.rest.feign;

import com.example.springbootdemos.rest.feignclient.client.PostClient;
import com.example.springbootdemos.rest.feignclient.model.PostData;
import com.example.springbootdemos.restdocs.rest.BaseControllerTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(outputDir = "target/snippets")
public class FeignRestControllerTest extends BaseControllerTest {

    @MockBean //Mock Bean crea un mock de PostClient
    private PostClient postClient;

    @Test
    void getPosts() throws Exception {
        List<PostData> postsList = new ArrayList<>();
        PostData postData = new PostData();
        postData.setId(1);
        postData.setBody("Mocked Body");
        postData.setTitle("Title mocked");
        postData.setUserId(2);
        PostData postData2 = new PostData();
        postData2.setId(2);
        postData2.setBody("Mocked Body 2");
        postData2.setTitle("Title mocked 2");
        postData2.setUserId(1);
        postsList.add(postData);
        postsList.add(postData2);

        when(postClient.findAll()).thenReturn(postsList);
        this.mockMvc.perform(get("/demo/rest/feign/jsonplaceholder/posts")
                .contentType((MediaType.APPLICATION_JSON))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("feign/jsonplaceholder/posts",
                        responseFields(
                                fieldWithPath("[].id").description("Post ID"),
                                fieldWithPath("[].body").description("Post Body"),
                                fieldWithPath("[].title").description("Post Title"),
                                fieldWithPath("[].userId").description("Post User ID")
                        )));

        verify(postClient).findAll();
        verify(postClient,times(1)).findAll();
    }

    @Test
    void getPost() throws Exception {
        PostData postData = new PostData();
        postData.setId(1);
        postData.setBody("Mocked Body");
        postData.setTitle("Title mocked");
        postData.setUserId(2);

        when(postClient.findById(1)).thenReturn(postData);
        this.mockMvc.perform(get("/demo/rest/feign/jsonplaceholder/posts/1")
                .contentType((MediaType.APPLICATION_JSON))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("feign/jsonplaceholder/posts",
                        responseFields(
                                fieldWithPath("id").description("Post ID"),
                                fieldWithPath("body").description("Post Body"),
                                fieldWithPath("title").description("Post Title"),
                                fieldWithPath("userId").description("Post User ID")
                        )));
        verify(postClient).findById(1);
        verify(postClient, times(1)).findById(1);
    }

    @Test
    void addPost() throws Exception {
        PostData postData = new PostData();
        postData.setId(1);
        postData.setBody("Mocked Body");
        postData.setTitle("Title mocked");
        postData.setUserId(2);

        when(postClient.create(any(PostData.class))).thenReturn(ResponseEntity.ok().build());
        String content = new ObjectMapper().writeValueAsString(postData);
        this.mockMvc.perform(post("/demo/rest/feign/jsonplaceholder/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("feign/jsonplaceholder/posts",
                        requestFields(
                                fieldWithPath("id").description("Post ID"),
                                fieldWithPath("body").description("Post Body"),
                                fieldWithPath("title").description("Post Title"),
                                fieldWithPath("userId").description("Post User ID")
                        ),
                        responseFields(
                                fieldWithPath("headers").description("HTTP response headers"),
                                fieldWithPath("statusCode").description("HTTP status message"),
                                fieldWithPath("statusCodeValue").description("HTTP status code"),
                                fieldWithPath("body").description("HTTP response body")
                        )));
        verify(postClient).create(any(PostData.class));
    }

    @Test
    void updatePost() throws Exception {
        PostData postData = new PostData();
        postData.setId(1);
        postData.setBody("Updated Mocked Body");
        postData.setTitle("Updated mocked title");
        postData.setUserId(2);

        when(postClient.update(1, postData)).thenReturn(postData);
        String content = new ObjectMapper().writeValueAsString(postData);
        this.mockMvc.perform(put("/demo/rest/feign/jsonplaceholder/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("feign/jsonplaceholder/posts",
                        requestFields(
                                fieldWithPath("id").description("Post ID"),
                                fieldWithPath("body").description("Post Body"),
                                fieldWithPath("title").description("Post Title"),
                                fieldWithPath("userId").description("Post User ID")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Post ID"),
                                fieldWithPath("body").description("Post Body"),
                                fieldWithPath("title").description("Post Title"),
                                fieldWithPath("userId").description("Post User ID")
                        )));
        verify(postClient).update(anyInt(), any(PostData.class));
    }

}
