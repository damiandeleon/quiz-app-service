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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
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

    @MockBean
    private ScoreDao scoreDao;

    private Score daoScore;
    private String daoJson;
    private List<Score> allScores = new ArrayList<>();
    private String allScoresJson;
    private List<Score> allScores2 = new ArrayList<>();
    private String allScoresJson2;


    @Before
    public void setUp() throws Exception {
    daoScore = new Score();
    daoScore.setId(999);
    daoScore.setUserId(45);
    daoScore.setQuizId(13);
    daoScore.setScore(92);

    daoJson = mapper.writeValueAsString(daoScore);


    Score score = new Score();
    score.setId(23);
    score.setUserId(45);
    score.setQuizId(2);
    score.setScore(88);
    allScores.add(score);
    allScores2.add(score);

    Score score2 = new Score();
    score2.setId(25);
    score2.setUserId(3);
    score2.setQuizId(2);
    score2.setScore(88);
    allScores.add(score2);

    allScoresJson = mapper.writeValueAsString(allScores);
    allScoresJson2 = mapper.writeValueAsString(allScores2);
    }

    @Test
    @Transactional
    public void shouldAddAScoreAndGetScoreByUserIdThenByQuizId() throws Exception {
        Score score3 = new Score();
        score3.setId(1);
        score3.setUserId(45);
        score3.setQuizId(13);
        score3.setScore(92);

        String jsonInput = mapper.writeValueAsString(score3);

        given(scoreDao.save(score3)).willReturn(daoScore);

        mockMvc.perform(
                        post("/score")
                                .content(jsonInput)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonInput));

        given(scoreDao.findListOfScoreByUserId(45)).willReturn(allScores2);

        mockMvc.perform(
                        get("/score/user/45")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(allScoresJson2));

        given(scoreDao.findListOfScoreByQuizId(2)).willReturn(allScores);

        mockMvc.perform(
                        get("/score/quiz/2")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(allScoresJson));
    }

}