package com.company.quizappservice.controller;

import com.company.quizappservice.dao.QuestionDao;
import com.company.quizappservice.dao.QuizDao;
import com.company.quizappservice.dto.Question;
import com.company.quizappservice.dto.Quiz;
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


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

    }

  @Test
  public void shouldCreateQuestion() throws Exception{

      Quiz quizOut = new Quiz();
      quizOut.setQuizName("Test3");
      quizOut.setCategory("Java");
      quizOut.setLevel("Medium");
      quizOut.setUserId(1);

      Question inputQuestion = new Question();
      inputQuestion.setQuestion("Test Question2");
      inputQuestion.setCorrectAnswer("correct2");
      inputQuestion.setWrongAnswerOne("wrong1");
      inputQuestion.setWrongAnswerTwo("wrong2");
      inputQuestion.setWrongAnswerThree("wrong3");

      List<Question> inputList = new ArrayList<>();
      inputList.add(inputQuestion);

      String inputJson = mapper.writeValueAsString(inputList);


      mockMvc.perform(post("/question")
                      .content(inputJson)
                      .contentType(MediaType.APPLICATION_JSON)
              )
              .andDo(print())
              .andExpect(status().isCreated());
    }


}
