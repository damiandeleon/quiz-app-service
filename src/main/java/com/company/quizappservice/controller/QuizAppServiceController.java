package com.company.quizappservice.controller;
import com.company.quizappservice.dao.UserDao;
import com.company.quizappservice.dto.User;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
public class QuizAppServiceController {

    @UpdateTimestamp
    private UserDao userRepo;


    @PostMapping("/login")
    public User getUserId(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        User loginUser =  userRepo.findByUsernameAndPassword(username, password);
        System.out.println(loginUser);
        return loginUser;
    }
}
