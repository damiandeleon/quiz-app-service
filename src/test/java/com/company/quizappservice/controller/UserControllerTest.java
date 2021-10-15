package com.company.quizappservice.controller;

import com.company.quizappservice.dao.UserDao;
import com.company.quizappservice.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private UserDao userDao;

    @org.junit.Before
    public void setUp() {
    }

    // TEST 0


    @Test
    public void addListToDataBase() throws Exception {


        User user1 = new User();
        user1.setUsername("number1");
        user1.setPassword("password1");
        user1.setFirstName("Number1");
        user1.setLastName("Person");

        String user1String = mapper.writeValueAsString(user1);

        mockMvc.perform(
                post("/user")
                        .content(user1String)
                        .contentType(MediaType.APPLICATION_JSON));

        User user2 = new User();
        user2.setUsername("number2");
        user2.setPassword("password2");
        user2.setFirstName("Number2");
        user2.setLastName("Person");

        String user2String = mapper.writeValueAsString(user2);

        mockMvc.perform(
                post("/user")
                        .content(user2String)
                        .contentType(MediaType.APPLICATION_JSON));

        User user3 = new User();
        user3.setUsername("number3");
        user3.setPassword("password3");
        user3.setFirstName("Number3");
        user3.setLastName("Person");

        String user3String = mapper.writeValueAsString(user3);
        mockMvc.perform(
                post("/user")
                        .content(user3String)
                        .contentType(MediaType.APPLICATION_JSON));

        User user4 = new User();
        user4.setUsername("number4");
        user4.setPassword("password4");
        user4.setFirstName("Number4");
        user4.setLastName("Person");

        String user4String = mapper.writeValueAsString(user4);


        mockMvc.perform(
                post("/user")
                        .content(user4String)
                        .contentType(MediaType.APPLICATION_JSON));
    }

    //TEST 1
    @Test
    public void shouldCreateAUserWithPostCommand() throws Exception {
        User user = new User();
        user.setUsername("LynnMyers");
        user.setFirstName("Lynn");
        user.setLastName("Myers");
        user.setPassword("password5");

        String jsonInput = mapper.writeValueAsString(user);

        mockMvc.perform(
                        post("/user")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    //TEST 2
    @Test
    public void shouldUpdateByIdPutMethod() throws Exception {
        User user = new User();
        user.setId(5);
        user.setFirstName("Delete");
        user.setLastName("MeLater");
        user.setUsername("deleteMeLater");
        user.setPassword("password5");

        String jsonInput = mapper.writeValueAsString(user);

        mockMvc.perform(
                        put("/user/5")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }


    // TEST 3
    @Test
    public void shouldGetAllUsers() throws Exception {

        mockMvc.perform(
                        get("/user")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }


    // TEST 4
    @Test
    public void shouldGetUserById() throws Exception {

        User user3 = new User();
        user3.setId(3);
        user3.setUsername("number3");
        user3.setPassword("password3");
        user3.setFirstName("Number3");
        user3.setLastName("Person");


        String user3String = mapper.writeValueAsString(user3);
        mockMvc.perform(
                        get("/user/3")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(user3String));

    }



    //TEST 5
    @Test
    public void shouldDeleteUserById() throws Exception{

        mockMvc.perform(
                        delete("/user/5"))
                .andExpect(status().isOk());
    }


    //TEST 6
    @Test
    public void shouldReturn400ErrorDueToEmptyVariable() throws Exception {
        User user = new User();
        user.setUsername("ICantBeCreated");
        // no First Name is being Set
        user.setLastName("Doe");
        user.setPassword("password");

        String jsonInput = mapper.writeValueAsString(user);

        mockMvc.perform(
                        post("/user")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest()
                );
    }
}