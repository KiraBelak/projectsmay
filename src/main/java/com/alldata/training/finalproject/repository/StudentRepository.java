package com.alldata.training.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alldata.training.finalproject.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
