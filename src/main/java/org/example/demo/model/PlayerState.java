package org.example.demo.model;

import lombok.Data;

@Data
public class PlayerState {

    // Position ---------------------------------------------------------------
    private float x;
    private float y;

    // Velocity ---------------------------------------------------------------
    private float vx;
    private float vy;

    // Other flags ------------------------------------------------------------
    private boolean onGround;

    private boolean isFacingRight;

    private int attackFrame; //reverse frame count

    private int hitCooldown;

    private float damage;


    public PlayerState(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PlayerState() {
    }

    /** clone() → immutable snapshot so clients cannot mutate state they receive */
    public PlayerState copy() {
        PlayerState p = new PlayerState(x, y);
        p.vx = vx;
        p.vy = vy;
        p.onGround = onGround;
        p.isFacingRight = isFacingRight;
        p.attackFrame = attackFrame;
        p.hitCooldown = hitCooldown;
        p.damage = damage;
        return p;
    }
}
