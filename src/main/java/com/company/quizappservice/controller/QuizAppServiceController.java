package com.company.quizappservice.controller;

import com.company.quizappservice.dao.UserDao;
import com.company.quizappservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizAppServiceController {

    @Autowired
    UserDao dao;


    @PostMapping(value = "/user")
    @ResponseStatus(value = HttpStatus.OK)
    public User createUser(@RequestBody User user){
        System.out.println("CREATING USER");
        return dao.addUser(user);
    }





}
