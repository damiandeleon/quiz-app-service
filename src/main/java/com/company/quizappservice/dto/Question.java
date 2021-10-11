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

    private String correctAns;
    private String[] wrongAns;

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

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public String[] getWrongAns() {
        return wrongAns;
    }

    public void setWrongAns(String[] wrongAns) {
        this.wrongAns = wrongAns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(quizId, question.quizId) && Objects.equals(correctAns, question.correctAns) && Arrays.equals(wrongAns, question.wrongAns);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, quizId, correctAns);
        result = 31 * result + Arrays.hashCode(wrongAns);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", correctAns='" + correctAns + '\'' +
                ", wrongAns=" + Arrays.toString(wrongAns) +
                '}';
    }
}
