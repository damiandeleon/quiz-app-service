package com.company.quizappservice.dao;

import com.company.quizappservice.dto.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreDao extends JpaRepository<Score, Integer> {

    List<Score> findListOfScoreByUserId(Integer userId);


    List<Score> findListOfScoreByQuizId(int quizId);

}