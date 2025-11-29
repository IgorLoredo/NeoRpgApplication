package com.neo.game.character.domain;

import com.neo.game.character.infrastructure.web.domain.model.valueobjects.Health;
import com.neo.game.character.infrastructure.web.domain.model.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthTest {

    @Test
    void shouldCreateWithValidValues() {
        Health health = new Health(10, 20);
        assertEquals(10, health.getCurrent());
        assertEquals(20, health.getMaximum());
        assertTrue(health.isAlive());
    }

    @Test
    void shouldNotAllowInvalidCurrent() {
        assertThrows(DomainException.class, () -> new Health(-1, 10));
        assertThrows(DomainException.class, () -> new Health(15, 10));
    }

    @Test
    void shouldClampDamageAtZero() {
        Health health = new Health(5, 10);
        Health damaged = health.takeDamage(10);
        assertEquals(0, damaged.getCurrent());
        assertFalse(damaged.isAlive());
    }

    @Test
    void shouldHealUpToMaximum() {
        Health health = new Health(5, 10);
        Health healed = health.heal(10);
        assertEquals(10, healed.getCurrent());
    }
}
