package com.company.quizappservice.controller;

import com.company.quizappservice.dao.QuizDao;
import com.company.quizappservice.dto.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class QuizController {

    @Autowired
    private QuizDao quizRepo;


    @PostMapping("/quiz")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Quiz createQuiz(@RequestBody Quiz quiz){
        System.out.println(quiz);
        quizRepo.save(quiz);
        return quiz;
    }


    @GetMapping("/quiz/{id}")
    public Optional<Quiz> getQuiz(@PathVariable Integer id){

        Optional<Quiz> returnQuiz = quizRepo.findById(id);

        return returnQuiz;
    }
}
