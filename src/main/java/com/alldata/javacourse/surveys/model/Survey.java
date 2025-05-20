package com.alldata.javacourse.surveys.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 10)
    private String code;
    @Column(length = 200)
    private String title;
    private Integer userId;
    private Status status;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_survey")
    private List<Question> questions = new ArrayList<>();
    private Integer activeQuestion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Integer getActiveQuestion() {
        return activeQuestion;
    }

    public void setActiveQuestion(Integer activeQuestion) {
        this.activeQuestion = activeQuestion;
    }
}
