package com.alldata.javacourse.surveys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(Vote.VotePk.class)
public class Vote {
    public record VotePk(Integer questionId, Integer userId) {
    }

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer questionId;
    @Id
    private Integer userId;
    private Integer optionId;

    public Vote() {
    }

    public Vote(Integer questionId, Integer userId, Integer optionId) {
        this.questionId = questionId;
        this.userId = userId;
        this.optionId = optionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getOptionId() {
        return optionId;
    }
}
