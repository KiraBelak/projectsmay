package com.alldata.training.finalproject.service;

import com.alldata.training.finalproject.model.Homework;
import com.alldata.training.finalproject.repository.HomeworkRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public List<Homework> getAllHomeworks() {
        return homeworkRepository.findAll();
    }

    public Homework saveHomework(Homework homework) {
        return homeworkRepository.save(homework);
    }
}
