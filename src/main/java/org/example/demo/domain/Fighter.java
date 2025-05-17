package org.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    Attack attack = new Attack();

    float weight = 1;
    int jumpPower = 600;
    int speed = 200;
    String imageUrl = "https://raw.githubusercontent.com/KiraBelak/projectsmay/rene-chavez/src/main/resources/static/img/char24.png";
}
