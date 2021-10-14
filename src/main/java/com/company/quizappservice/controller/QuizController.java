package com.company.quizappservice.controller;

import com.company.quizappservice.dao.QuizDao;
import com.company.quizappservice.dto.Quiz;
import com.company.quizappservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class QuizController {

    @Autowired
    private QuizDao quizRepo;

    @CrossOrigin
    @PostMapping("/quiz")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Quiz createQuiz(@RequestBody Quiz quiz){
        System.out.println(quiz);
        quizRepo.save(quiz);
        return quiz;
    }

    @CrossOrigin
    @GetMapping("/quiz/{id}")
    public Optional<Quiz> getQuiz(@PathVariable Integer id){

        Optional<Quiz> returnQuiz = quizRepo.findById(id);

        return returnQuiz;
    }

    @GetMapping("/quiz")
    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }
}
