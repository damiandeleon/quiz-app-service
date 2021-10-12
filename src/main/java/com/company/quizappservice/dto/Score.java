package com.company.quizappservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernationLazyInitializer", "handler"})
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer quizId;
    private String scorePercent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getScorePercent() {
        return scorePercent;
    }

    public void setScorePercent(String scorePercent) {
        this.scorePercent = scorePercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(id, score.id) && Objects.equals(userId, score.userId) && Objects.equals(quizId, score.quizId) && Objects.equals(scorePercent, score.scorePercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, quizId, scorePercent);
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", userId=" + userId +
                ", quizId=" + quizId +
                ", scorePercent='" + scorePercent + '\'' +
                '}';
    }
}
