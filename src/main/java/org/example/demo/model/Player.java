package org.example.demo.model;

import lombok.Getter;
import org.example.demo.domain.Fighter;

@Getter
public class Player {
    private final PlayerState state;

    private final Fighter fighter;

    public Player(PlayerState state, Fighter fighter) {
        this.state   = state;
        this.fighter = fighter;
    }
}
