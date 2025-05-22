package org.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.demo.model.state.PlayerState;

@Getter
@AllArgsConstructor
public class Player {
    private final String name;

    private final PlayerState state;

    private final Fighter fighter;

}
