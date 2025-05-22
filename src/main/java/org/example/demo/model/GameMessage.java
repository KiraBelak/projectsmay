package org.example.demo.model;


import lombok.Data;
import org.example.demo.model.state.GameState;

import java.time.Instant;

@Data
public class GameMessage {

    public enum MessageType { INPUT, STATE }

    private MessageType type;
    private String player;   // who sent it
    private Long fighterId; // chosen fighter
    private String key;      // e.g. LEFT, RIGHT, JUMP  (only for INPUT)
    private boolean pressed;
    private GameState state; // whole / partial world state (only for STATE)
    private Instant timestamp = Instant.now();


}
