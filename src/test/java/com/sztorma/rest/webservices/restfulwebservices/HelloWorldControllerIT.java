package com.sztorma.rest.webservices.restfulwebservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Hello world string")
    public void testHelloWorldText() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello-world"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Hello World"));
    }

    @Test
    @DisplayName("Hello world bean")
    public void testHelloWorldBean() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello-world-bean"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hello World"));
    }

    @Test
    @DisplayName("Hello world path variable")
    public void testHelloWorldPathVariable() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello-world/path-variable/Charlie"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hello World, Charlie"));
    }

}
