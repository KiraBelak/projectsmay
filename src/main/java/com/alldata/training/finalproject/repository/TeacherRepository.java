package com.alldata.training.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alldata.training.finalproject.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}