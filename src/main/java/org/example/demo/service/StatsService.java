package org.example.demo.service;

import jakarta.transaction.Transactional;
import org.example.demo.domain.Stats;
import org.example.demo.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class StatsService {

    private StatsRepository statsRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    // Other methods
    public Stats getStatsByName(String name) {
        return statsRepository.findById(name).orElse(null);
    }

    public Stats saveStats(Stats stats) {
        return statsRepository.save(stats);
    }

    public List<Stats> getAllStats() {
        return statsRepository.findAll();
    }
    @Transactional
    public void update(String playerName, Consumer<Stats> updater) {
        Stats stats = statsRepository.findById(playerName)
                .orElseGet(() -> new Stats(playerName));
        updater.accept(stats);
        statsRepository.save(stats);
    }

}
