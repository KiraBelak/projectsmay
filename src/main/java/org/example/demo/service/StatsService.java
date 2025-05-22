package org.example.demo.service;

import jakarta.transaction.Transactional;
import org.example.demo.model.Player;
import org.example.demo.model.Stats;
import org.example.demo.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Service
public class StatsService {

    private StatsRepository statsRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public Stats getStatsByName(String name) {
        return statsRepository.findById(name).orElse(null);
    }

    public Stats saveStats(Stats stats) {
        return statsRepository.save(stats);
    }

    public List<Stats> getAllStats() {
        return statsRepository.findAll();
    }

    // Cache for stats to avoid DB calls at 30fps
    private final Map<String, Stats> statsCache = new ConcurrentHashMap<>();

    /**
     * Get stats for player.
     * @param player The player.
     * @return The stats for the player.
     */
    public Stats statsOf(Player player) {
        // load once from DB or create empty
        return statsCache.computeIfAbsent(player.getName(),
                n -> this.getStatsByName(n) != null
                        ? this.getStatsByName(n)
                        : new Stats(n));
    }

    /**
     * Persist all stats in cache to DB.
     */
    public void flushCache() {
        statsCache.values().forEach(this::saveStats);
    }
}
