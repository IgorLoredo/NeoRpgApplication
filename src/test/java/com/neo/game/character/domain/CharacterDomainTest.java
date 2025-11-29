package com.neo.game.character.domain;

import com.neo.game.character.infrastructure.web.domain.model.Character;
import com.neo.game.character.infrastructure.web.domain.model.enums.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterDomainTest {

    @Test
    void shouldCreateWarriorWithBaseStatsAndHealth() {
        Character warrior = Character.create("Hero_", Job.WARRIOR);
        assertEquals(Job.WARRIOR, warrior.getJob());
        assertEquals(20, warrior.getHealth().getMaximum());
        assertEquals(20, warrior.getHealth().getCurrent());
        assertEquals(10, warrior.getStats().getStrength());
        assertEquals(5, warrior.getStats().getDexterity());
        assertEquals(5, warrior.getStats().getIntelligence());
    }

    @Test
    void shouldCalculateAttackAndSpeedModifiersByJob() {
        Character warrior = Character.create("Hero_", Job.WARRIOR);
        Character thief = Character.create("Rogue_", Job.THIEF);
        Character mage = Character.create("Mage__", Job.MAGE);

        assertTrue(warrior.attackModifier() > thief.attackModifier() - 5); // ballpark sanity
        assertTrue(thief.speedModifier() >= warrior.speedModifier());
        assertTrue(mage.attackModifier() >= mage.getStats().getIntelligence());
    }
}
