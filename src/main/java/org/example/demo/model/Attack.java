package org.example.demo.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Attack {

    @Range(min = 10, max = 200, message = "Attack sprite size must be between 10 and 200")
    int size = 32;

    @Range(min = 2, max = 200, message = "Attack cooldown must be between 2 and 200")
    int cooldown;

    @Range(min = 1, max = 200, message = "Attack damage must be between 1 and 200")
    int damage = 5;

    @Range(min = 1, max = 200, message = "Attack knockback must be between 1 and 200")
    int knockback = 100;
}
