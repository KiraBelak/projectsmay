package org.example.demo.model;

import lombok.Data;

@Data
public class Fighter {
    Attack attack = new Attack();
    int weight = 1;
    int jumpPower = 600;
    int speed = 200;
}
