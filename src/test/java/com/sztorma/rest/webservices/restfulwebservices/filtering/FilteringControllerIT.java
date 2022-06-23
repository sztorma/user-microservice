package com.sztorma.rest.webservices.restfulwebservices.filtering;

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
public class FilteringControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Filtering param")
    public void testFilterField() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/filtering"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.field3").doesNotExist());
    }

    @Test
    @DisplayName("Filtering param in list")
    public void testFilterFieldInList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/filtering-list"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].field3").doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].field3").doesNotExist());
    }
}
