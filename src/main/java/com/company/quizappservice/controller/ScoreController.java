package com.company.quizappservice.controller;

import com.company.quizappservice.dao.ScoreDao;
import com.company.quizappservice.dto.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ScoreController {

    @Autowired
    private ScoreDao scoreRepo;

    @CrossOrigin
    @PostMapping("/score")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Score createScore(@RequestBody Score score) {
        scoreRepo.save(score);
        return score;
    }
    @CrossOrigin
    @GetMapping("/score")
    public List<Score> getAllScores(){
        return scoreRepo.findAll();
    }
}