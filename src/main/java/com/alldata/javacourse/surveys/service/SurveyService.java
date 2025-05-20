package com.alldata.javacourse.surveys.service;

import com.alldata.javacourse.surveys.model.Survey;
import com.alldata.javacourse.surveys.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public List<Survey> findByUserId(Integer userId) {
        return surveyRepository.findByUserId(userId);
    }

    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    public Survey save(Survey entity) {
        return surveyRepository.save(entity);
    }

    public Optional<Survey> findById(String id) {
        return surveyRepository.findById(id);
    }

    public Optional<Survey> findByCode(String code) {
        return surveyRepository.findByCode(code);
    }

    public void deleteById(String s) {
        surveyRepository.deleteById(s);
    }
}
