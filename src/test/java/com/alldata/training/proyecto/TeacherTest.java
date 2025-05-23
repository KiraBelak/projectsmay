package com.alldata.training.proyecto;

import org.junit.jupiter.api.Test;

import com.alldata.training.finalproject.model.Teacher;
import com.alldata.training.finalproject.repository.TeacherRepository;
import com.alldata.training.finalproject.service.TeacherService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class TeacherTest {

    @Test
    public void testObtenerPorId() {
        TeacherRepository mockRepo = mock(TeacherRepository.class);
        TeacherService service = new TeacherService(mockRepo);

        Teacher teacher = new Teacher("Josue", "Martinez");
        when(mockRepo.findById(1L)).thenReturn(Optional.of(teacher));

        Optional<Teacher> result = service.getTeacherById(1L);
        assertNotNull(result);
        assertEquals("Martinez", result.get().getLastname());
    }
}
