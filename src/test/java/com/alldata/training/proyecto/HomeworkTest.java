package com.alldata.training.proyecto;

import org.junit.jupiter.api.Test;

import com.alldata.training.finalproject.model.Homework;
import com.alldata.training.finalproject.model.Student;
import com.alldata.training.finalproject.model.Teacher;
import com.alldata.training.finalproject.repository.HomeworkRepository;
import com.alldata.training.finalproject.repository.StudentRepository;
import com.alldata.training.finalproject.repository.TeacherRepository;
import com.alldata.training.finalproject.service.HomeworkService;
import com.alldata.training.finalproject.service.StudentService;
import com.alldata.training.finalproject.service.TeacherService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

public class HomeworkTest {

    @Test
    public void testObtenerPorId() {

        //Teacher
        TeacherRepository teacherMockRepo = mock(TeacherRepository.class);
        TeacherService teacherService = new TeacherService(teacherMockRepo);

        Teacher teacher = new Teacher("Josue", "Martinez");
        when(teacherMockRepo.findById(1L)).thenReturn(Optional.of(teacher));

        //Student
        StudentRepository studentMockRepo = mock(StudentRepository.class);
        StudentService studentService = new StudentService(studentMockRepo);

        Student student = new Student("Emmanuel", "Mediano", 6);
        when(studentMockRepo.findById(1L)).thenReturn(Optional.of(student));

        HomeworkRepository homeworkRepositoryMockRepo = mock(HomeworkRepository.class);
        HomeworkService homeworkService = new HomeworkService(homeworkRepositoryMockRepo);

        Homework homework = new Homework(
            "Homework description goes here!", 
            student,
            teacher,
            LocalDate.of(2025,6,20)
        );

        when(homeworkRepositoryMockRepo.findById(1L)).thenReturn(Optional.of(homework));

        Optional<Homework> result = homeworkService.getHomeworkById(1L);
        assertNotNull(result);
        assertEquals(student.getFirstname(), result.get().getStudent().getFirstname());
        assertEquals(teacher.getFirstname(), result.get().getTeacher().getFirstname());
    }
}
