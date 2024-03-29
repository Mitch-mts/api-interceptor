package mts.mtech.apiinterceptor.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloRestController.class)
class HelloRestControllerTest {
    @Autowired
    HelloRestController restController = new HelloRestController();
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("controller integration test")
    void hello() {
        String result = restController.hello("World");
        assertThat("Hello, World").isEqualTo(result);
    }

    @Test
    void integrationTest() throws Exception {
        RequestBuilder request = get("/hello");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertThat("Hello, World").isEqualTo(result.getResponse().getContentAsString());
    }

    @Test
    void testHelloWithName() throws Exception{
        mockMvc.perform(get("/hello?name=Mitch"))
                .andExpect(content().string("Hello, Mitch"));
    }
}