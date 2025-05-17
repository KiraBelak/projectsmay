package org.example.demo.service;

import org.example.demo.domain.Fighter;
import org.example.demo.domain.Scene;
import org.example.demo.model.*;
import org.example.demo.model.GameMessage.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Scene scene = Scene.createTestScene();

    private final Map<String, Player> players = new ConcurrentHashMap<>();
    // For every player we remember which keys are currently pressed
    private final Map<String, Set<String>> keyStates = new ConcurrentHashMap<>();

    private final SimpMessagingTemplate template;


    private final FighterService fighterService;

    @Autowired
    public GameService(SimpMessagingTemplate template, FighterService fighterService) {
        this.template = template;
        this.fighterService = fighterService;
    }

    public void processInput(GameMessage msg) {
        if (msg.getType() != MessageType.INPUT) return;

        // Create player if it doesn't exist yet.
        //TODO: move to proper player creation
        Fighter fighter = msg.getFighterId() == null ? new Fighter() : fighterService.getFighterById(msg.getFighterId());
        var p = players.computeIfAbsent(msg.getPlayer(),
                name -> new Player(
                            new PlayerState((float) (Math.random() * 500), (float) (Math.random() * 300)),// spawn point
                            fighter));
        p.getState().setFighterId(fighter.getId());

        // another player for testing
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
        var ps = p1.getState();
        if (ps.getHitCooldown() < MAX_HIT_COOLDOWN && ps.getHitCooldown() >= 0) {
            ps.setHitCooldown(ps.getHitCooldown() + 1);
        }
        final int attackSize = p1.getFighter().getAttack().getSize();
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
                            other.getState().getX(), other.getState().getY(), attackSize, attackSize))
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

    public static void sceneCollisions(Scene scene, PlayerState ps) {
        int playerSize = 32; //TODO: custom sizes
        Rectangle playerRect = new Rectangle(
                Math.round(ps.getX() - playerSize / 2f), Math.round(ps.getY()) - playerSize,
                playerSize, playerSize);

        ps.setOnGround(false);

        for (Rectangle block : scene.getBlocks()) {

            if (!playerRect.intersects(block)) continue;

            // AABB resolution
            float dxLeft   = block.x + block.width  - playerRect.x;          // move player right
            float dxRight  = block.x - (playerRect.x + playerRect.width);    // move player left
            float dyUp     = block.y - (playerRect.y + playerRect.height);   // move player up
            float dyDown   = block.y + block.height - playerRect.y;          // move player down

            // pick the axis with the smallest penetration
            float absX = Math.abs(dxLeft) < Math.abs(dxRight) ? dxLeft : dxRight;
            float absY = Math.abs(dyUp)   < Math.abs(dyDown)  ? dyUp   : dyDown ;

            if (Math.abs(absX) < Math.abs(absY)) {
                // resolve horizontally
                ps.setX(ps.getX() + absX);
                ps.setVx(0);
            } else {
                // resolve vertically
                ps.setY(ps.getY() + absY);
                ps.setVy(0);
                if (absY < 0) { // player landed on top of a block
                    ps.setOnGround(true);
                }
            }
            // update playerRect for potential further collisions
            playerRect.setLocation(Math.round(ps.getX()), Math.round(ps.getY()));
        }
        // check if player is outside the scene
        if (ps.getX() < 0 || ps.getX() > scene.getWidth() || ps.getY() < 0 || ps.getY() > scene.getHeight()) {
            ps.setX(400);
            ps.setY(50);
            ps.setVx(0);
            ps.setVy(0);
            ps.setDamage(0);
            playerRect.setLocation(Math.round(ps.getX()), Math.round(ps.getY()));
        }
    }


    //  game-loop
    @Scheduled(fixedRate = 33) // ~30 fps
    public void tick() {

        float dt = 0.033f; // seconds

        // iterate every player
        players.forEach((name, py) -> {

            Set<String> keys = keyStates.getOrDefault(name, Set.of());
            var ps = py.getState();

            boolean isHit = ps.getHitCooldown() >= 0 && ps.getHitCooldown() < 12;
            if (!isHit) {
                // Horizontal movement
                if (keys.contains("LEFT")) {
                    ps.setVx(-py.getFighter().getSpeed());
                    ps.setFacingRight(false);
                }
                if (keys.contains("RIGHT")) {
                    ps.setVx(py.getFighter().getSpeed());
                    ps.setFacingRight(true);
                }
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
            ps.setVy(Math.min(ps.getVy() + GRAVITY * dt * py.getFighter().getWeight(), MAX_VELOCITY));

            // Integrate
            ps.setX(ps.getX() + ps.getVx() * dt);
            ps.setY(ps.getY() + ps.getVy() * dt);

            sceneCollisions(scene, ps);
        });

        // broadcast immutable snapshot
        GameState snapshot = new GameState(players);
        GameMessage stateMsg = new GameMessage();
        stateMsg.setType(MessageType.STATE);
        stateMsg.setState(snapshot);

        template.convertAndSend("/topic/game-state", stateMsg);
    }
}
