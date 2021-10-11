package com.company.quizappservice.controller;

import com.company.quizappservice.dao.ScoreRepository;
import com.company.quizappservice.dao.UserDao;
import com.company.quizappservice.dto.Score;
import com.company.quizappservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuizAppServiceController {

    @Autowired
    private UserDao userRepo;

    @Autowired
    private ScoreRepository scoreRepo;


    @PostMapping(value = "/user")
    @ResponseStatus(value = HttpStatus.OK)
    public User createUser(@RequestBody User user){
        userRepo.save(user);
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);

        if (!user.isPresent()) {
            return null;
        }
        return user.get();
    }

    @PutMapping(value = "/user/{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id) {
        if(user.getId() == null)  {
            user.setId(id);
        }

        if(user.getId() != id) {
            throw new IllegalArgumentException("User ID must match your parameter given");
        }
        userRepo.save(user);
    }

    @DeleteMapping(value = "user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepo.deleteById(id);
    }

    @PostMapping(value = "/score")
    @ResponseStatus(value = HttpStatus.OK)
    public Score createScore(@RequestBody Score score) {
        scoreRepo.save(score);
        return score;
    }

    @GetMapping
    public List<Score> getAllScores(){
        return scoreRepo.findAll();
    }


}
