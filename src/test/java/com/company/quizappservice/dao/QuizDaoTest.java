package com.company.quizappservice.dao;


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
public class QuizDaoTest {

    @Autowired
    QuizDao quizDao;

    @Before
    public void setUp() {
        quizDao.deleteAll();
    }

    @Test
    @Transactional
    public void shouldAddAndFindByCategory() {
        Quiz quiz = new Quiz();

        quiz.setName("Quiz One");
        quiz.setCategory("Java");
        quiz.setLevel("Easy");

        quizDao.save(quiz);

        Quiz quiz2 = new Quiz();

        quiz2.setName("Quiz Two");
        quiz2.setCategory("Java");
        quiz2.setLevel("Medium");

        quizDao.save(quiz2);

        Quiz quiz3 = new Quiz();

        quiz3.setName("Quiz Three");
        quiz3.setCategory("Node");
        quiz3.setLevel("Easy");

        quizDao.save(quiz3);

        List<Quiz> javaList = quizDao.findByCategory("Java");
        List<Quiz> nodeList = quizDao.findByCategory("Node");

        assertEquals(2, javaList.size());
        assertEquals(1, nodeList.size());

    }

    @Test
    @Transactional
    public void shouldAddAndFindByLevel() {
        Quiz quiz = new Quiz();

        quiz.setName("Quiz One");
        quiz.setCategory("Java");
        quiz.setLevel("Easy");

        quizDao.save(quiz);

        Quiz quiz2 = new Quiz();

        quiz2.setName("Quiz Two");
        quiz2.setCategory("Java");
        quiz2.setLevel("Medium");

        quizDao.save(quiz2);

        Quiz quiz3 = new Quiz();

        quiz3.setName("Quiz Three");
        quiz3.setCategory("Node");
        quiz3.setLevel("Easy");

        quizDao.save(quiz3);

        List<Quiz> easyList = quizDao.findByLevel("Easy");
        List<Quiz> mediumList = quizDao.findByLevel("Medium");

        assertEquals(2, easyList.size());
        assertEquals(1, mediumList.size());

    }

    @Test
    @Transactional
    public void shouldAddAndFindByCategoryAndLevel() {
        Quiz quiz = new Quiz();

        quiz.setName("Quiz One");
        quiz.setCategory("Java");
        quiz.setLevel("Easy");

        quizDao.save(quiz);

        Quiz quiz2 = new Quiz();

        quiz2.setName("Quiz Two");
        quiz2.setCategory("Java");
        quiz2.setLevel("Medium");

        quizDao.save(quiz2);

        Quiz quiz3 = new Quiz();

        quiz3.setName("Quiz Three");
        quiz3.setCategory("Node");
        quiz3.setLevel("Easy");

        quizDao.save(quiz3);

        List<Quiz> quizList = quizDao.findByCategoryAndLevel("Java", "Easy");

        assertEquals(1, quizList.size());

    }



}
