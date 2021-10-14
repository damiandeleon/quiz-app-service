package com.company.quizappservice.controller;

import com.company.quizappservice.dto.Score;
import com.company.quizappservice.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

// *****  ATTENTION!!! MUST DROP MYSQL SCHEMAS AND RESTART POSTMAN/INSOMNIA BEFORE RUNNING TEST ****


    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCreateAUserWithPostCommand() throws Exception {
        User user = new User();
        user.setUsername("LynnNose");
        user.setFirstName("Lynn");
        user.setLastName("Nose");
        user.setPassword("password2");

        String jsonInput = mapper.writeValueAsString(user);

        mockMvc.perform(
                        post("/user")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        mockMvc.perform(
                        get("/user")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetUserById() throws Exception {
        mockMvc.perform(
                        get("/user/1")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateByIdPutMethod() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("LynnMyers");
        user.setFirstName("Lynn");
        user.setLastName("Myers");
        user.setPassword("password2");

        String jsonInput = mapper.writeValueAsString(user);

        mockMvc.perform(
                        put("/user/2")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteUserById() throws Exception{

    User user = new User();
    user.setFirstName("Damian");
    user.setLastName("Deleon");
    user.setUsername("damiandeleon");
    user.setPassword("password");

    String jsonInput = mapper.writeValueAsString(user);

        mockMvc.perform(
        post("/user")
                .content(jsonInput)
                .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(
        delete("/user/1"))
                .andExpect(status().isOk());
    }
}