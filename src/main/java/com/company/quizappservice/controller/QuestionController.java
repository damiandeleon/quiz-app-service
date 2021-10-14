package com.company.quizappservice.controller;

import com.company.quizappservice.dao.QuestionDao;
import com.company.quizappservice.dao.QuizDao;
import com.company.quizappservice.dto.Question;
import com.company.quizappservice.dto.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionDao questionRepo;

    @Autowired
    private QuizDao quizRepo;


    @PostMapping("/question")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<Question> createQuestion(@RequestBody List<Question> questionList){
        List<Quiz> quizSet = quizRepo.findAll();
        Quiz lastQuiz = quizSet.get(quizSet.size() - 1);
        Integer quizId = lastQuiz.getId();

        for(Question question : questionList){
            question.setQuizId(quizId);
            question.setQuestion(question.getQuestion());
            question.setCorrectAnswer(question.getCorrectAnswer());
            question.setWrongAnswerOne(question.getWrongAnswerOne());
            question.setWrongAnswerTwo(question.getWrongAnswerTwo());
            question.setWrongAnswerThree(question.getWrongAnswerThree());
            System.out.println(question);
            questionRepo.save(question);
        }
        return questionList;
    }
}
