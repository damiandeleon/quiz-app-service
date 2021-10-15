package com.company.quizappservice.dao;

import com.company.quizappservice.dto.Quiz;
import com.company.quizappservice.dto.Score;
import com.company.quizappservice.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreDaoTest {

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    UserDao userDao;

    @Autowired
    QuizDao quizDao;

    @Before
    public void setUp() throws Exception {
        scoreDao.deleteAll();
        userDao.deleteAll();
        quizDao.deleteAll();
    }

    //Test findScoreByUserId


    @Test
    public void shouldReturnScoreByUserId() {

        User testUser = new User();
        testUser.setId(1);
        testUser.setUsername("LynnMyers");
        testUser.setFirstName("Lynn");
        testUser.setLastName("Myers");
        testUser.setPassword("password1");
        userDao.save(testUser);

        Score testScore = new Score();
        testScore.setScore(95);
        testScore.setUserId(1);
        testScore.setQuizId(1);
        scoreDao.save(testScore);

        Quiz quiz = new Quiz();
        quiz.setId(1);
        quiz.setQuizName("Quiz One");
        quiz.setCategory("Java");
        quiz.setLevel("Easy");
        quizDao.save(quiz);

        Score findScoreByUID = testScore;
        Score findScoreByQID = testScore;

        assertEquals(findScoreByUID, scoreDao.findScoreByUserId(1));
        assertEquals(findScoreByQID, scoreDao.findScoreByQuizId(1));

    }
}