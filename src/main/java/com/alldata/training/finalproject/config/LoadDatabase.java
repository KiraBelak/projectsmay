package com.alldata.training.finalproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

import com.alldata.training.finalproject.model.Homework;
import com.alldata.training.finalproject.model.Student;
import com.alldata.training.finalproject.model.Teacher;
import com.alldata.training.finalproject.repository.HomeworkRepository;
import com.alldata.training.finalproject.repository.StudentRepository;
import com.alldata.training.finalproject.repository.TeacherRepository;
import com.google.type.DateTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository, TeacherRepository teacherRepository,
                                    HomeworkRepository homeworkRepository) {
        return args -> {

            Student newStudent = new Student("Juan", "Rocha", 6);
            log.info("Inserting into Students " + studentRepository.save(newStudent));
            log.info("Inserting into Students " + studentRepository.save(new Student("Josue", "Torres", 6)));
            log.info("Inserting into Students " + studentRepository.save(new Student("Carlos", "Torres", 5)));
            log.info("Inserting into Students " + studentRepository.save(new Student("Miguel", "Jaramillo", 4)));
            log.info("Inserting into Students " + studentRepository.save(new Student("Ivan", "Guzman", 4)));

            Teacher newTeacher = new Teacher("Jose", "Rosales");
            log.info("Inserting into Teachers " + teacherRepository.save(newTeacher));
            log.info("Inserting into Teachers " + teacherRepository.save(new Teacher("Omar", "Contreras")));
            log.info("Inserting into Teachers " + teacherRepository.save(new Teacher("Alberto", "Quezada")));

            log.info("Inserting into Teachers " + homeworkRepository.save(new Homework("Resumen pagina 50, 51 y 52 del libro de Historia", 
                newStudent, newTeacher, LocalDate.of(2025,6,10))));           
        };
    }
}
