package com.company.quizappservice.controller;

import com.company.quizappservice.dao.ScoreDao;
import com.company.quizappservice.dao.UserDao;
import com.company.quizappservice.dto.Score;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ScoreDao scoreDao;

    @Before
    public void setUp() {
        scoreDao.deleteAll();
    }

    @Test
    @Transactional
    public void shouldAddAScoreAndGetScoreByUserIdThenByQuizId() throws Exception {
        Score score = new Score();
        score.setId(1);
        score.setUserId(45);
        score.setQuizId(13);
        score.setScore(92);

        String jsonInput = mapper.writeValueAsString(score);

        mockMvc.perform(
                        post("/score")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonInput));

        mockMvc.perform(
                        get("/score/user/45")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(jsonInput));

        mockMvc.perform(
                        get("/score/quiz/13")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(jsonInput));
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