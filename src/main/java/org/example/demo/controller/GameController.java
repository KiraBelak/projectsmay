package org.example.demo.controller;


import org.example.demo.model.GameMessage;
import org.example.demo.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/game.input")
    public void onPlayerInput(GameMessage msg) {
        gameService.processInput(msg);
    }
}