package com.company.quizappservice.controller;

import com.company.quizappservice.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuizAppServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldLogInUser() throws Exception {
        User user = new User();
        user.setFirstName("Damian");
        user.setLastName("Deleon");
        user.setUsername("damiandeleon");
        user.setPassword("password");

        User loginUser = new User();
        loginUser.setUsername("damiandeleon");
        user.setPassword("password");

        String jsonInput = mapper.writeValueAsString(user);
        String jsonInput2 = mapper.writeValueAsString(loginUser);

        mockMvc.perform(
                post("/user")
                        .content(jsonInput)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(
                        post("/login")
                                .content(jsonInput2)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}