package org.example.demo.model.state;

import lombok.Data;

/**
 * Player state. Position, velocity, and other state information.
 */
@Data
public class PlayerState {

    // Position ---------------------------------------------------------------
    private float x;
    private float y;

    // Velocity ---------------------------------------------------------------
    private float vx;
    private float vy;

    /** Whether the player is on the ground */
    private boolean onGround;

    /** Whether the player is facing right */
    private boolean isFacingRight;

    /** Attack duration; reverse frame count */
    private int attackFrame;

    /** Frames until player can be hit again */
    private int hitCooldown;

    /** Current damage taken */
    private float damage;

    /** Selected fighter */
    private Long fighterId; //TODO: this shouldn't be here. Not required for every frame update.

    public PlayerState(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PlayerState() {}

    /**
     * Immutable copy of this state.
     * @return a copy of this state
     */
    public PlayerState copy() {
        PlayerState p = new PlayerState(x, y);
        p.vx = vx;
        p.vy = vy;
        p.onGround = onGround;
        p.isFacingRight = isFacingRight;
        p.attackFrame = attackFrame;
        p.hitCooldown = hitCooldown;
        p.damage = damage;
        p.fighterId = fighterId;
        return p;
    }
}
