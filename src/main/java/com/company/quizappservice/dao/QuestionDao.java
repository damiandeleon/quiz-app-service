package com.company.quizappservice.dao;

import com.company.quizappservice.dto.Question;
import com.company.quizappservice.dto.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByQuizId(Integer quizId);


}
