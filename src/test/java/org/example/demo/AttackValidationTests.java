package org.example.demo;

import jakarta.validation.Validator;
import org.example.demo.model.Attack;
import org.example.demo.model.Fighter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AttackValidationTests {
    @Autowired
    Validator validator;

    @Test
    void AttackIsValid() {
        var a = new Attack(32, 5, 10, 100);
        assertThat(validator.validate(a)).isEmpty();
    }

    @Test
    void invalidAttackShouldGenerateViolations() {
        var a = new Attack(0, 0, 0, 0);
        var errors = validator.validate(a);
        assertThat(errors).hasSize(4);
    }
}
