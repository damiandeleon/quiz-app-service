package com.company.quizappservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name = "PRIVATE_SEQ", sequenceName="quiz_sequence")
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PRIVATE_SEQ")
    private Integer id;
    private String quizName;
    private String category;
    private String level;
    private Integer userId;
    @OneToMany(mappedBy = "quizId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> question;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id) && Objects.equals(quizName, quiz.quizName) && Objects.equals(category, quiz.category) && Objects.equals(level, quiz.level) && Objects.equals(userId, quiz.userId) && Objects.equals(question, quiz.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizName, category, level, userId, question);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", quizName='" + quizName + '\'' +
                ", category='" + category + '\'' +
                ", level='" + level + '\'' +
                ", userId=" + userId +
                ", question=" + question +
                '}';
    }
}
