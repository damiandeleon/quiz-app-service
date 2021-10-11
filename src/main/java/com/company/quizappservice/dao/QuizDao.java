package com.company.quizappservice.dao;

import com.company.quizappservice.dto.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByCategory(String category);
    List<Quiz> findByLevel(String level);
    List<Quiz> findByCategoryAndLevel(String category, String level);
}
