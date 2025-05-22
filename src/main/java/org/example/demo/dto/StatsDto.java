package org.example.demo.dto;

import org.example.demo.model.Stats;

public record StatsDto(
        String name,
        int matches,
        int kos,
        int defeats,
        int damageDealt,
        int damageTaken,
        int attacks,
        int hitsDealt,
        int hitsTaken,
        float accuracy,
        String lastFighter
) {
    public static StatsDto fromEntity(Stats s) {
        return new StatsDto(
                s.getName(),
                s.getMatches(),
                s.getKos(),
                s.getDefeats(),
                s.getDamageDealt(),
                s.getDamageTaken(),
                s.getAttacks(),
                s.getHitsDealt(),
                s.getHitsTaken(),
                s.getAccuracy(),
                s.getLastFighter()
        );
    }
}

