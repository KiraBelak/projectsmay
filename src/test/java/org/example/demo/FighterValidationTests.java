package org.example.demo;

import jakarta.validation.ConstraintViolation;
import org.example.demo.model.Fighter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FighterValidationTests {
    @Autowired
    Validator validator;

    @Test
    void FighterIsValid() {
        // lower bound
        var a = new Fighter();
        a.setWeight(0.1f);
        a.setJumpPower(100);
        a.setSpeed(20);
        // middle
        var b = new Fighter();
        b.setWeight(1.0f);
        b.setJumpPower(600);
        b.setSpeed(200);
        // upper bound
        var c = new Fighter();
        c.setWeight(2.0f);
        c.setJumpPower(1000);
        c.setSpeed(1000);

        assertThat(validator.validate(a)).isEmpty();
        assertThat(validator.validate(b)).isEmpty();
        assertThat(validator.validate(c)).isEmpty();
    }

    @Test
    void invalidFighterShouldGenerateViolations() {
        Fighter f = new Fighter();
        f.setWeight(0);
        f.setJumpPower(99);
        f.setSpeed(10);

        var errors = validator.validate(f);

        assertThat(errors).hasSize(3);
    }

    @Test
    void invalidFighter_aboveUpperBound_shouldGenerateViolations() {
        Fighter f = new Fighter();
        f.setWeight(2.1f);
        f.setJumpPower(1001);
        f.setSpeed(1001);

        var errors = validator.validate(f);

        assertThat(errors).hasSize(3);
    }
}
