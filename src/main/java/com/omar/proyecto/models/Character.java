package com.omar.proyecto.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @ElementCollection
    private List<String> movements;

    public Character() {}

    public Character(Long id, String name, int age, List<String> movements) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.movements = movements;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public List<String> getMovements() { return movements; }
    public void setMovements(List<String> movements) { this.movements = movements; }
}