package com.omar.proyecto.dto;

import java.util.List;

public class CharacterDTO {
    private String name;
    private int age;
    private List<String> movements;

    public CharacterDTO() {}

    public CharacterDTO(String name, int age, List<String> movements) {
        this.name = name;
        this.age = age;
        this.movements = movements;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public List<String> getMovements() { return movements; }
    public void setMovements(List<String> movements) { this.movements = movements; }
}