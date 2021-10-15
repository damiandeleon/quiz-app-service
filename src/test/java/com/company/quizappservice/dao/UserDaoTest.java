package com.company.quizappservice.dao;

import com.company.quizappservice.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao.deleteAll();
    }

    @Test
    public void shouldAddAndFindByUsernameAndPassword() {

        User user = new User();
        user.setUsername("testMeNow");
        user.setPassword("password");
        user.setFirstName("Test");
        user.setLastName("Me");
        userDao.save(user);

        User user2 = new User();
        user2.setUsername("testMeNow2");
        user2.setPassword("password2");
        user2.setFirstName("Test2");
        user2.setLastName("Me2");
        userDao.save(user2);



        User userList = user;


        assertEquals(userList, userDao.findByUsernameAndPassword("testMeNow", "password"));

    }
}