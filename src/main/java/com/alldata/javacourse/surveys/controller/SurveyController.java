package com.alldata.javacourse.surveys.controller;

import com.alldata.javacourse.surveys.dto.SurveyInfoDto;
import com.alldata.javacourse.surveys.model.Survey;
import com.alldata.javacourse.surveys.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/survey")
public class SurveyController {
    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public ResponseEntity<List<SurveyInfoDto>> getSurveys() {
        List<SurveyInfoDto> surveys = surveyService.findAll().stream().map(SurveyInfoDto::new).toList();
        return ResponseEntity.ok(surveys);
    }

    @GetMapping("byUserId/{userId}")
    public List<SurveyInfoDto> findByUserId(@PathVariable("userId") Integer userId) {
        return surveyService.findByUserId(userId).stream().map(SurveyInfoDto::new).toList();
    }

    @GetMapping("/{id}")
    public Optional<Survey> findById(@PathVariable String id) {
        return surveyService.findById(id);
    }

    @GetMapping("/code/{code}")
    public Optional<Survey> findByCode(@PathVariable String code) {
        return surveyService.findByCode(code);
    }

    @PostMapping
    public Survey save(@RequestBody Survey entity) {
        return surveyService.save(entity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        surveyService.deleteById(id);
    }
}
