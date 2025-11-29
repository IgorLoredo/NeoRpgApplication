package com.neo.game.domain.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {
    @Test
    void shouldReturnCorrectBaseStats() {
        assertEquals(20, Job.WARRIOR.getBaseHealth());
        assertEquals(10, Job.WARRIOR.getStrength());
        assertEquals(5, Job.WARRIOR.getDexterity());
        assertEquals(5, Job.WARRIOR.getIntelligence());

        assertEquals(15, Job.THIEF.getBaseHealth());
        assertEquals(4, Job.THIEF.getStrength());
        assertEquals(10, Job.THIEF.getDexterity());
        assertEquals(4, Job.THIEF.getIntelligence());

        assertEquals(12, Job.MAGE.getBaseHealth());
        assertEquals(5, Job.MAGE.getStrength());
        assertEquals(6, Job.MAGE.getDexterity());
        assertEquals(10, Job.MAGE.getIntelligence());
    }
}
