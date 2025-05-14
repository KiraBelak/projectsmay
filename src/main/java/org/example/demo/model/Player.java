package org.example.demo.model;

import lombok.Getter;

@Getter
public class Player {
    private final PlayerState state;

    private final Fighter fighter;

    public Player(PlayerState state, Fighter fighter) {
        this.state   = state;
        this.fighter = fighter;
    }
}
