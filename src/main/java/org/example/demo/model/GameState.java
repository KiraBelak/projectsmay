package org.example.demo.model;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Immutable representation of the whole world
 * that is serialized and broadcast to every client
 * each simulation tick.
 */
@Getter
public class GameState {

    // Key = player name, value = state at this exact tick
    private final Map<String, PlayerState> players;

    public GameState(Map<String, Player> livePlayers) {
        // Make deep copies so that the snapshot
        // can never be modified accidentally
        this.players = Collections.unmodifiableMap(
                livePlayers.entrySet()
                        .stream()
                        .map(e -> Map.entry(e.getKey(), e.getValue().getState()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue().copy())));
    }

}
