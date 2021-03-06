package com.sztorma.rest.webservices.restfulwebservices.user;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceIT {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static String stringDate;

    @Autowired
    private MockMvc mockMvc;

    private static final String UUID_STRING =
        "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    @BeforeAll
    public static void setup() {
        stringDate = DATE_FORMAT.format(new Date());
    }

    @Test
    @DisplayName("Retrieve all users")
    public void testRetrievAllUsers() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].uuid", Matchers.matchesPattern(UUID_STRING)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Adam"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthDate").value(stringDate))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].uuid", Matchers.matchesPattern(UUID_STRING)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Eve"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].birthDate").value(stringDate))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value("3"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].uuid", Matchers.matchesPattern(UUID_STRING)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Jack"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].birthDate").value(stringDate));
    }

    @Test
    @DisplayName("Retrieve one user")
    public void testRetrieveOneUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users/2"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$.uuid", Matchers.matchesPattern(UUID_STRING)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Eve"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate").value(stringDate))
            .andExpect(MockMvcResultMatchers.jsonPath("$._links").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$._links").isNotEmpty());
    }

    @Test
    @DisplayName("User not found")
    public void testRetrieveNoUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users/200"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Delete user")
    public void testDeleteUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Delete non existing user")
    public void testDeleteNonExistingUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/10000"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Save new user")
    public void testSaveNewUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Csabi\", \"birthDate\": \"2001-11-14\"}"))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(header().string("Location", "http://localhost/users/4"));
    }

    @Test
    @DisplayName("Attempt save invalid user")
    public void testAttemptSaveInvalidUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"s\", \"birthDate\": \"3001-11-14\"}"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.invalidParams.name").value("name is too short at least 2 characters required"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.invalidParams.birthDate").value("Can not be future"));
    }

}
