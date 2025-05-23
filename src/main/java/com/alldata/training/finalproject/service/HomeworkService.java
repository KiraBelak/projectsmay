package com.alldata.training.finalproject.service;

import com.alldata.training.finalproject.model.Homework;
import com.alldata.training.finalproject.model.Student;
import com.alldata.training.finalproject.repository.HomeworkRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public List<Homework> getAllHomeworks() {
        return homeworkRepository.findAll();
    }

    public Optional<Homework> getHomeworkById(Long homeworkId) {
        return homeworkRepository.findById(homeworkId);
    }

    public Homework saveHomework(Homework homework) {
        return homeworkRepository.save(homework);
    }
}
