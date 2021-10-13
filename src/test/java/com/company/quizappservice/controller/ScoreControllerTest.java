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
public class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldAddAScore() throws Exception {
        Score score = new Score();
        score.setId(1);
        score.setUserId(1);
        score.setQuizId(1);
        score.setScorePercent("92%");

        String jsonInput = mapper.writeValueAsString(score);

        mockMvc.perform(
                        post("/score")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetAllScores()throws Exception{
        mockMvc.perform(
                        get("/score")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}