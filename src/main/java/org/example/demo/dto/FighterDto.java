package org.example.demo.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.example.demo.model.Attack;
import org.example.demo.model.Fighter;
import org.hibernate.validator.constraints.Range;

public record FighterDto(
        @Range(min = 10,  max = 200)
        int attackSize,

        @Range(min = 2,   max = 200)
        int attackCooldown,

        @Range(min = 1,   max = 200)
        int attackDamage,

        @Range(min = 1,   max = 200)
        int attackKnockback,

        @DecimalMin(value = "0.0", inclusive = false)
        @DecimalMax("2.0")
        float weight,

        @Range(min = 100, max = 1000)
        int jumpPower,

        @Range(min = 20,  max = 1000)
        int speed,

        @NotBlank
        String imageUrl
) {

    public static FighterDto fromEntity(Fighter fighter) {
        return new FighterDto(
            fighter.getAttack().getSize(),
            fighter.getAttack().getCooldown(),
            fighter.getAttack().getDamage(),
            fighter.getAttack().getKnockback(),
            fighter.getWeight(),
            fighter.getJumpPower(),
            fighter.getSpeed(),
            fighter.getImageUrl()
        );
    }

    public static Fighter toEntity(FighterDto fighterDto) {
        var f = new Fighter();
        var a = new Attack();
        a.setCooldown(fighterDto.attackCooldown);
        a.setDamage(fighterDto.attackDamage);
        a.setKnockback(fighterDto.attackKnockback);
        a.setSize(fighterDto.attackSize);
        f.setAttack(a);
        f.setWeight(fighterDto.weight);
        f.setJumpPower(fighterDto.jumpPower);
        f.setSpeed(fighterDto.speed);
        f.setImageUrl(fighterDto.imageUrl);
        return f;
    }

}

