package org.example.demo.service;

import org.example.demo.model.*;
import org.example.demo.model.GameMessage.MessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Authoritative game service. Applies player input, performs simple platform-physics,
 * and sends state to everyone.
 */
@Service
public class GameService {

    private static final float GRAVITY      = 2000f;
    private static final float MAX_VELOCITY = 320f;

    private static final int MAX_HIT_COOLDOWN = 60;


    private final Map<String, Player> players = new ConcurrentHashMap<>();
    // For every player we remember which keys are currently pressed
    private final Map<String, Set<String>> keyStates = new ConcurrentHashMap<>();

    private final SimpMessagingTemplate template;

    public GameService(SimpMessagingTemplate template) {
        this.template = template;
    }



    public void processInput(GameMessage msg) {
        if (msg.getType() != MessageType.INPUT) return;

        var p = players.computeIfAbsent(msg.getPlayer(),
                name -> new Player(
                            new PlayerState((float) (Math.random() * 500), (float) (Math.random() * 300)),// spawn point
                            new Fighter()));
        // another random player
        players.computeIfAbsent(msg.getPlayer() + "_cpu",
            name -> new Player(
                        new PlayerState(p.getState().getX() + 50, (float) (Math.random() * 300)),
                        new Fighter()));
        keyStates.computeIfAbsent(msg.getPlayer(), k -> ConcurrentHashMap.newKeySet());

        if (msg.isPressed()) {
            keyStates.get(msg.getPlayer()).add(msg.getKey());
        } else {
            keyStates.get(msg.getPlayer()).remove(msg.getKey());
        }
    }



    void attackTick(Set<String> keys, Player p1) {
        final int attackSize = 32;
        var ps = p1.getState();
        if (ps.getHitCooldown() < MAX_HIT_COOLDOWN && ps.getHitCooldown() >= 0) {
            ps.setHitCooldown(ps.getHitCooldown() + 1);
        }
        // Attack
        if (ps.getAttackFrame() > 0) {
            ps.setAttackFrame(ps.getAttackFrame() - 1);
            //attack sprite is left or right of player
            float attackSpriteX = ps.isFacingRight() ? ps.getX() + attackSize : ps.getX() - attackSize;
            // attack bounds
            Rectangle attackBounds = new Rectangle((int)attackSpriteX, (int)ps.getY(), attackSize, attackSize);
            // check collision with other players. Hit players modify their vx and vy.
            players.values().parallelStream()
                    .filter(other -> !other.getState().equals(ps))
                    .filter(other -> other.getState().getHitCooldown() < 0 || other.getState().getHitCooldown() > 12)
                    .filter(other -> attackBounds.intersects(
                            other.getState().getX(), other.getState().getY(), 32, 32))
                    .forEach(p2 -> {
                        var p2s = p2.getState();
                        var f1 = p1.getFighter();
                        var f2 = p2.getFighter();
                        var kb = f1.getAttack().getKnockback();
                        var fkb = kb * (p2s.getDamage() / 5 + 1) / f2.getWeight();
                        p2s.setVx(p2s.getVx() + (ps.isFacingRight() ? fkb : -fkb));
                        p2s.setVy(p2s.getVy() - (100 + fkb) / 2);
                        p2s.setHitCooldown(0);
                        p2s.setDamage(p2s.getDamage() + f1.getAttack().getDamage());
                    });
        }
        else if (keys.contains("ATTACK") && ps.getAttackFrame() == 0) {
            ps.setAttackFrame(5);
        }

        // if current player is hit, reduce current vx each tick until 0
        if (ps.getHitCooldown() >= 0) {
            if ((int)Math.abs(ps.getVx()) <= 0) {
                ps.setVx(0);
                ps.setHitCooldown(-1);
            }
            else ps.setVx(ps.getVx() * 0.8f); // how much x speed is reduced each frame when hit
        }
    }

    //  game-loop
    @Scheduled(fixedRate = 33) // ~30 fps
    public void tick() {

        float dt = 0.033f; // seconds

        // -------- iterate every player ------
        players.forEach((name, py) -> {

            Set<String> keys = keyStates.getOrDefault(name, Set.of());
            var ps = py.getState();

            // Horizontal movement
            if (keys.contains("LEFT")) {
                ps.setVx(-py.getFighter().getSpeed());
                ps.setFacingRight(false);
            }
            if (keys.contains("RIGHT")) {
                ps.setVx(py.getFighter().getSpeed());
                ps.setFacingRight(true);
            }
            if (!keys.contains("LEFT") && !keys.contains("RIGHT") && ps.getHitCooldown() < 0) {
                ps.setVx(0);
            }

            // Jump
            if (keys.contains("JUMP") && ps.isOnGround()) {
                ps.setVy(-py.getFighter().getJumpPower());
                ps.setOnGround(false);
            }

            attackTick(keys, py);

            // Gravity
            ps.setVy(Math.min(ps.getVy() + GRAVITY * dt, MAX_VELOCITY));

            // Integrate
            ps.setX(ps.getX() + ps.getVx() * dt);
            ps.setY(ps.getY() + ps.getVy() * dt);

            // Very naive ground collision (y = 400 is ground)
            if (ps.getY() >= 400) {
                ps.setY(400);
                ps.setVy(0);
                ps.setOnGround(true);
            }
        });

        // -------- broadcast immutable snapshot
        GameState snapshot = new GameState(players);
        GameMessage stateMsg = new GameMessage();
        stateMsg.setType(MessageType.STATE);
        stateMsg.setState(snapshot);

        template.convertAndSend("/topic/game-state", stateMsg);
    }
}
