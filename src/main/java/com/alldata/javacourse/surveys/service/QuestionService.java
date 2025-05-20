package com.alldata.javacourse.surveys.service;

import com.alldata.javacourse.surveys.model.Question;
import com.alldata.javacourse.surveys.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public <S extends Question> S save(S entity) {
        return questionRepository.save(entity);
    }

    public Optional<Question> findById(Integer id) {
        return questionRepository.findById(id);
    }

    public void deleteById(Integer id) {
        questionRepository.deleteById(id);
    }
}
