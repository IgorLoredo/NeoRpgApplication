package com.neo.game.domain.model.valueobjects;

import com.neo.game.domain.model.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsTest {
    @Test
    void shouldCreateStatsWithValidValues() {
        Stats stats = new Stats(5, 10, 15);
        assertEquals(5, stats.getStrength());
        assertEquals(10, stats.getDexterity());
        assertEquals(15, stats.getIntelligence());
    }

    @Test
    void shouldNotAllowNegativeValues() {
        assertThrows(DomainException.class, () -> new Stats(-1, 0, 0));
        assertThrows(DomainException.class, () -> new Stats(0, -1, 0));
        assertThrows(DomainException.class, () -> new Stats(0, 0, -1));
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        Stats s1 = new Stats(1, 2, 3);
        Stats s2 = new Stats(1, 2, 3);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void shouldTestToString() {
        Stats stats = new Stats(1, 2, 3);
        String str = stats.toString();
        assertTrue(str.contains("str=1"));
        assertTrue(str.contains("dex=2"));
        assertTrue(str.contains("int=3"));
    }
}
