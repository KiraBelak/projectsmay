package com.alldata.javacourse.surveys.controller;

import com.alldata.javacourse.surveys.model.Question;
import com.alldata.javacourse.surveys.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> findAll() {
        return questionService.findAll();
    }

    @PostMapping
    public Question save(@RequestBody Question entity) {
        return questionService.save(entity);
    }

    @GetMapping("/{id}")
    public Optional<Question> findById(@PathVariable Integer id) {
        return questionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        questionService.deleteById(id);
    }
}
