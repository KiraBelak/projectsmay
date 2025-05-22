package org.example.demo.model;

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

    public void incrementDamageDealt(int damage) {
        this.damageDealt += damage;
    }
    public void incrementDamageTaken(int damage) {
        this.damageTaken += damage;
    }
    public void incrementHitsDealt() {
        this.hitsDealt += 1;
    }
    public void incrementHitsTaken() {
        this.hitsTaken += 1;
    }

    public void incrementMatches() {
        this.matches += 1;
    }

    public void incrementKos() {
        this.kos += 1;
    }

    public void incrementDefeats() {
        this.defeats += 1;
    }
    public void incrementAttacks() {
        this.attacks += 1;
    }


    public Stats(String name) {
        this();
        this.name = name;
    }
}
