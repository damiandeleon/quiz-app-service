package com.company.quizappservice.controller;

import com.company.quizappservice.dao.ScoreDao;
import com.company.quizappservice.dto.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ScoreController {

    @Autowired
    private ScoreDao scoreRepo;


    @PostMapping("/score")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Score createScore(@RequestBody Score score) {
        scoreRepo.save(score);
        return score;
    }

    @GetMapping("/score")
    public List<Score> getAllScores(){
        return scoreRepo.findAll();
    }


    @GetMapping("/score/quiz/{quizId}")
    public List<Score> getScoreByQuizId(@PathVariable int quizId){
        List<Score> userScore = new ArrayList<>();
        userScore = scoreRepo.findScoreByQuizId(quizId);
        return userScore;
    }

    @GetMapping("/score/user/{userId}")
    public List<Score> getScoreByUserId(@PathVariable int userId){
        List<Score> userScore = new ArrayList<>();
        userScore = scoreRepo.findListOfScoreByUserId(userId);
        return userScore;
    }

}