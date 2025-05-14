package org.example.demo.model;

import lombok.Data;

@Data
public class Attack {
    int width = 32;
    int height = 32;
    int cooldown;
    int damage = 5;
    int knockback = 100;
}
