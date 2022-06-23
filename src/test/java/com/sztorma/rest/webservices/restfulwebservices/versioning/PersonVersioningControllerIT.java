package com.sztorma.rest.webservices.restfulwebservices.versioning;

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
public class PersonVersioningControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test Person V1 versioning")
    public void testPersonVersioningV1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/person"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bob Charlie"));
    }

    @Test
    @DisplayName("Test Person V2 versioning")
    public void testPersonVersioningV2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/person"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name.firstName").value("Bob"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name.lastName").value("Charlie"));
    }
}
