package com.alldata.training.proyecto;

import org.junit.jupiter.api.Test;

import com.alldata.training.finalproject.model.Student;
import com.alldata.training.finalproject.repository.StudentRepository;
import com.alldata.training.finalproject.service.StudentService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class StudentTest {

    @Test
    public void testObtenerPorId() {
        StudentRepository mockRepo = mock(StudentRepository.class);
        StudentService service = new StudentService(mockRepo);

        Student student = new Student("Emmanuel", "Mediano", 6);
        when(mockRepo.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> result = service.getStudentById(1L);
        assertNotNull(result);
        assertEquals("Emmanuel", result.get().getFirstname());
    }
}
