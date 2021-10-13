package com.company.quizappservice.controller;

import com.company.quizappservice.dao.QuestionDao;
import com.company.quizappservice.dao.QuizDao;
import com.company.quizappservice.dao.ScoreDao;
import com.company.quizappservice.dao.UserDao;
import com.company.quizappservice.dto.Question;
import com.company.quizappservice.dto.Quiz;
import com.company.quizappservice.dto.Score;
import com.company.quizappservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin
public class QuizAppServiceController {



    @Autowired
    private UserDao userRepo;

    @Autowired
    private ScoreDao scoreRepo;

    @Autowired
    private QuizDao quizRepo;

    @Autowired
    private QuestionDao questionRepo;

    @CrossOrigin
    @PostMapping("/quiz")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Quiz createQuiz(@RequestBody Quiz quiz){
        quizRepo.save(quiz);
        return quiz;
    }

    @CrossOrigin
    @PostMapping("/question")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<Question> createQuestion(@RequestBody List<Question> questionList){
        List<Quiz> quizSet = quizRepo.findAll();
        Quiz lastQuiz = quizSet.get(quizSet.size() - 1);
        Integer quizId = lastQuiz.getId();

        for(Question question : questionList){
            question.setQuizId(quizId);
            question.setQuestion(question.getQuestion());
            question.setCorrectAns(question.getCorrectAns());
            question.setWrongAns1(question.getWrongAns1());
            question.setWrongAns2(question.getWrongAns2());
            question.setWrongAns3(question.getWrongAns3());
            System.out.println(question);
            questionRepo.save(question);
        }
        return questionList;


    }
    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepo.deleteById(id);
    }

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

    @CrossOrigin
    @PostMapping("/login")
    public User getUserId(@RequestBody User user){


        String username = user.getUsername();
        String password = user.getPassword();
        User loginUser =  userRepo.findByUsernameAndPassword(username, password);
        System.out.println(loginUser);
        return loginUser;
    }

}
