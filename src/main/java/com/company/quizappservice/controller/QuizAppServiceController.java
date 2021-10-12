package com.company.quizappservice.controller;

import com.company.quizappservice.dao.ScoreDao;
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
    private ScoreDao scoreRepo;


    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        userRepo.save(user);
        return user;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);

        if (!user.isPresent()) {
            return null;
        }
        return user.get();
    }

    @PutMapping("/user/{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id) {
        if(user.getId() == null)  {
            user.setId(id);
        }

        if(user.getId() != id) {
            throw new IllegalArgumentException("User ID must match your parameter given");
        }
        userRepo.save(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepo.deleteById(id);
    }

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

    @GetMapping("/login")
    public User getUserId(@RequestBody User user){


//        User user = new User();
        String username = user.getUsername();
        String password = user.getPassword();
        User loginUser =  userRepo.findByUsernameAndPassword(username, password);
        System.out.println(loginUser);
        return loginUser;
    }


}
