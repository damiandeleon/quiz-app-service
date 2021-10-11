package com.company.quizappservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String category;
    private String level;

    @OneToMany(mappedBy = "quizId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Question> question;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Set<Question> question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id) && Objects.equals(name, quiz.name) && Objects.equals(category, quiz.category) && Objects.equals(level, quiz.level) && Objects.equals(question, quiz.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, level, question);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", level='" + level + '\'' +
                ", question=" + question +
                '}';
    }
}
