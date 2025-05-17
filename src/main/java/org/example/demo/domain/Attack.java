package org.example.demo.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Attack {
    int size = 32;
    int cooldown;
    int damage = 5;
    int knockback = 100;
}
