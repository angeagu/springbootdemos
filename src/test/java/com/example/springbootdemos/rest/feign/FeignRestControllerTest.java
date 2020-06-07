package com.example.springbootdemos.rest.feign;

import com.example.springbootdemos.rest.feignclient.client.PostClient;
import com.example.springbootdemos.rest.feignclient.model.PostData;
import com.example.springbootdemos.rest.shared.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(outputDir = "target/snippets")
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
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("jsonplaceholder/posts",
                        responseFields(
                                fieldWithPath("id").description("Post ID"),
                                fieldWithPath("body").description("Post Body"),
                                fieldWithPath("title").description("Post Title"),
                                fieldWithPath("userId").description("Post User ID")
                        )));
    }


}
