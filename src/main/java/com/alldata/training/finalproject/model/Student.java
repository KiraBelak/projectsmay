package com.alldata.training.finalproject.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Integer grade;
 
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Homework> homeworks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Student() {

    }

    public Student(String firstname, String lastname, Integer grade) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.grade = grade;
    }

    /*
    public static class StudentBuilder {
        private Long id;
        private String firstname;
        private String lastname;
        private Integer grade;
 
        public StudentBuilder id(Long id) {
            this.id = id;
            return this;
        }
 
        public StudentBuilder name(String firstname) {
            this.firstname = firstname;
            return this;
        }
 
        public StudentBuilder description(String lastname) {
            this.lastname = lastname;
            return this;
        }
 
        public StudentBuilder price(Integer grade) {
            this.grade = grade;
            return this;
        }
 
        public Student build() {
            return new Student(id, firstname, lastname, grade);
        }
    }
    */
}
