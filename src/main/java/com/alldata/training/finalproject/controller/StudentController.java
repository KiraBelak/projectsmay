package com.alldata.training.finalproject.controller;

import org.springframework.web.bind.annotation.*;

import com.alldata.training.finalproject.model.Student;
import com.alldata.training.finalproject.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getall")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
