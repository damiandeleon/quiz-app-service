package com.company.quizappservice.dao;

import com.company.quizappservice.dto.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    List<Score> findScoreById(Integer userId);
}