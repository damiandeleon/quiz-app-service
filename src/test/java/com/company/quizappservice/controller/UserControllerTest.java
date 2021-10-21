package com.company.quizappservice.controller;

import com.company.quizappservice.dao.UserDao;
import com.company.quizappservice.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Optional;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

// *****  ATTENTION!!! MUST DROP MYSQL SCHEMAS AND RESTART POSTMAN/INSOMNIA BEFORE RUNNING TEST ****


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDao dao;

    private ObjectMapper mapper = new ObjectMapper();

    private User daoUser;
    private String daoJson;
    private List<User> allUsers = new ArrayList<>();
    private String allUsersJson;

    @Before
    public void setUp() throws Exception {
        daoUser = new User();
        daoUser.setId(22);
        daoUser.setUsername("number1");
        daoUser.setPassword("password1");
        daoUser.setFirstName("Number1");
        daoUser.setLastName("Person");

        daoJson = mapper.writeValueAsString(daoUser);

        User user = new User();
        user.setId(555);
        user.setUsername("number1");
        user.setPassword("password1");
        user.setFirstName("Number1");
        user.setLastName("Person");
        allUsers.add(user);

        allUsersJson = mapper.writeValueAsString(allUsers);
    }
    // TEST 1
    @Test
    public void shouldCreateUserWithPostCommand() throws Exception {
        User user1 = new User();
        user1.setId(22);
        user1.setUsername("number1");
        user1.setPassword("password1");
        user1.setFirstName("Number1");
        user1.setLastName("Person");

        String user1String = mapper.writeValueAsString(user1);

        given(dao.save(user1)).willReturn(daoUser);

        mockMvc.perform(
                        post("/user")
                                .content(user1String)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(daoJson))
        ;
    }

    // TEST 2
    @Test
    public void shouldReturnUserById() throws Exception {


        given(dao.findById(22)).willReturn(java.util.Optional.ofNullable(daoUser));
        mockMvc.perform(
                        get("/user/22"))
                .andExpect(status().isOk())
                .andExpect(content().json(daoJson));

    }

    // TEST 3
    @Test
    public void shouldUpdateByIdPutMethod() throws Exception {
        User user = new User();
        user.setId(555);
        user.setFirstName("Delete");
        user.setLastName("MeLater");
        user.setUsername("deleteMeLater");
        user.setPassword("password5");

        String jsonInput = mapper.writeValueAsString(user);

        mockMvc.perform(
                        put("/user/555")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    // TEST 4
    @Test
    public void shouldGetAllUsers() throws Exception {

        given(dao.findAll()).willReturn(allUsers);

        mockMvc.perform(
                        get("/user")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allUsersJson));
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