package com.company.quizappservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer quizId;

    private String question;
    private String correctAns;
    private String wrongAns1;
    private String wrongAns2;
    private String wrongAns3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public String getWrongAns1() {
        return wrongAns1;
    }

    public void setWrongAns1(String wrongAns1) {
        this.wrongAns1 = wrongAns1;
    }

    public String getWrongAns2() {
        return wrongAns2;
    }

    public void setWrongAns2(String wrongAns2) {
        this.wrongAns2 = wrongAns2;
    }

    public String getWrongAns3() {
        return wrongAns3;
    }

    public void setWrongAns3(String wrongAns3) {
        this.wrongAns3 = wrongAns3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) && Objects.equals(quizId, question1.quizId) && Objects.equals(question, question1.question) && Objects.equals(correctAns, question1.correctAns) && Objects.equals(wrongAns1, question1.wrongAns1) && Objects.equals(wrongAns2, question1.wrongAns2) && Objects.equals(wrongAns3, question1.wrongAns3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizId, question, correctAns, wrongAns1, wrongAns2, wrongAns3);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", question='" + question + '\'' +
                ", correctAns='" + correctAns + '\'' +
                ", wrongAns1='" + wrongAns1 + '\'' +
                ", wrongAns2='" + wrongAns2 + '\'' +
                ", wrongAns3='" + wrongAns3 + '\'' +
                '}';
    }
}
