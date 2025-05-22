package org.example.demo.controller;

import org.example.demo.model.Stats;
import org.example.demo.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public ResponseEntity<List<Stats>> getAllStats() {
        List<Stats> stats = statsService.getAllStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Stats> getStatsByName(String name) {
        Stats stats = statsService.getStatsByName(name);
        return ResponseEntity.ok(stats);
    }
}
