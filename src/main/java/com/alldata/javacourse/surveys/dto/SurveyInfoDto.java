package com.alldata.javacourse.surveys.dto;

import com.alldata.javacourse.surveys.model.Status;
import com.alldata.javacourse.surveys.model.Survey;

public record SurveyInfoDto(Integer id, String title, Status status, Integer userId, int questionsCount) {
    public SurveyInfoDto(Survey survey) {
        this(survey.getId(), survey.getTitle(), survey.getStatus(), survey.getUserId(), survey.getQuestions().size());
    }
}
