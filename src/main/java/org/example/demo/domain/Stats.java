package org.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stats {
    @Id
    String name;

    int matches;
    int kos;
    int defeats;
    int damageDealt;
    int damageTaken;
    int attacks;
    int hitsDealt;
    int hitsTaken;
    String lastFighter; //image url
    String lastHitBy;

    @Column(insertable = false, updatable = false)
    float accuracy;

    public Stats(String name) {
        this();
        this.name = name;
    }
}
