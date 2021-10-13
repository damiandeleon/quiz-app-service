package com.company.quizappservice.dao;


import com.company.quizappservice.dto.Question;
import com.company.quizappservice.dto.Quiz;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionDaoTest {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    @Before
    public void setUp() {
        questionDao.deleteAll();
    }

    @Test
    @Transactional
    public void shouldAddAndFindByQuizId(){

        Quiz quiz = new Quiz();

        quiz.setQuizName("Quiz One");
        quiz.setCategory("Java");
        quiz.setLevel("Easy");

        quizDao.save(quiz);

        Question ques1 = new Question();

        ques1.setQuizId(quiz.getId());
        ques1.setQuestion("What is Java?");
        ques1.setCorrectAnswer("A rad programming language.");
        ques1.setWrongAnswerOne("A name for coffee.");
        ques1.setWrongAnswerTwo("A name for a band.");
        ques1.setWrongAnswerThree("None of your business");

        questionDao.save(ques1);

        Question ques2 = new Question();

        ques2.setQuizId(quiz.getId());
        ques2.setQuestion("What is Spring Boot?");
        ques2.setCorrectAnswer("A rad framework.");
        ques2.setWrongAnswerOne("A name for shoes.");
        ques2.setWrongAnswerTwo("What you get in the month of March.");
        ques2.setWrongAnswerThree("I wish I knew");

        questionDao.save(ques2);

        List<Question> quesList = questionDao.findByQuizId(quiz.getId());

        assertEquals(2, quesList.size());
    }

}
