package com.omar.proyecto.models;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private ArrayList<String> movements;

    public Character(long id, String name, int age, ArrayList<String> movements) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.movements = movements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public ArrayList<String> getMovements() {
        return movements;
    }

    public void setMovements(String newMovement){
        this.movements.add(newMovement);
    }
}