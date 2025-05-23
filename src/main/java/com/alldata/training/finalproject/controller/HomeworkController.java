package com.alldata.training.finalproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alldata.training.finalproject.exception.NoStudentFoundException;
import com.alldata.training.finalproject.exception.NoTeacherFoundException;
import com.alldata.training.finalproject.model.Homework;
import com.alldata.training.finalproject.model.Student;
import com.alldata.training.finalproject.model.Teacher;
import com.alldata.training.finalproject.service.HomeworkService;
import com.alldata.training.finalproject.service.StudentService;
import com.alldata.training.finalproject.service.TeacherService;

@RestController
@RequestMapping("/api/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public HomeworkController(HomeworkService homeworkService, StudentService studentService,
                                TeacherService teacherService) {
        this.homeworkService = homeworkService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/getall")
    public List<Homework> getAllHomeworks() {
        return homeworkService.getAllHomeworks();
    }

    @PostMapping("/create")
    public Homework create(@RequestBody Homework homework) {
        Student student = studentService.getStudentById(homework.getStudent().getId())
            .orElseThrow(() -> new NoStudentFoundException("Student with ID: " + homework.getStudent().getId() + " does not exist"));

        Teacher teacher = teacherService.getTeacherById(homework.getTeacher().getId())
            .orElseThrow(() -> new NoTeacherFoundException("Teacher with ID: " + homework.getTeacher().getId() + " does not exist"));
        
        Homework newHomework = new Homework(homework.getDescription(), student, teacher, homework.getDeadline());        
        return homeworkService.saveHomework(newHomework);
    }
}

