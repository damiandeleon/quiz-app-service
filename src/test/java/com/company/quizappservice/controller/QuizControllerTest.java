package com.company.quizappservice.controller;


import com.company.quizappservice.dao.QuestionDao;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    QuestionDao questionDao;



    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCreateQuiz() throws Exception{

        Quiz quiz = new Quiz();
        quiz.setQuizName("Test1");
        quiz.setCategory("Java");
        quiz.setLevel("Medium");
        quiz.setUserId(1);

        String inputJson = mapper.writeValueAsString(quiz);

        mockMvc.perform(post("/quiz")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldFindQuizById() throws Exception {

//        Question question = new Question();
//        question.setQuizId(3);
//        question.setQuestion("Test Question");
//        question.setCorrectAnswer("correct");
//        question.setWrongAnswerOne("wrong1");
//        question.setWrongAnswerTwo("wrong2");
//        question.setWrongAnswerThree("wrong3");
//        question = questionDao.save(question);
//
        List<Question> qList = new ArrayList<>();
        qList = questionDao.findByQuizId(15);

        Quiz outputQuiz = new Quiz();
        outputQuiz.setId(15);
        outputQuiz.setQuizName("Test1");
        outputQuiz.setCategory("Java");
        outputQuiz.setLevel("Medium");
        outputQuiz.setUserId(1);
        outputQuiz.setQuestion(qList);



        String outputJson = mapper.writeValueAsString(outputQuiz);
        System.out.println(outputQuiz);

        mockMvc.perform(get("/quiz/15"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));



    }
}
