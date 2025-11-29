package com.neo.game.domain.model;

import com.neo.game.domain.model.enums.Job;
import com.neo.game.domain.model.valueobjects.Health;
import com.neo.game.domain.model.valueobjects.Stats;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    void shouldLevelUpAndUpdateTimestamp() throws InterruptedException {
        Character character = Character.create("Hero_", Job.WARRIOR);
        LocalDateTime before = character.getUpdatedAt();
        Thread.sleep(1);
        character.levelUp();
        assertEquals(2, character.getLevel());
        assertTrue(character.getUpdatedAt().isAfter(before));
    }


    @Test
    void shouldAddExperienceAndUpdateTimestamp() throws InterruptedException {
        Character character = Character.create("Hero_", Job.WARRIOR);
        LocalDateTime before = character.getUpdatedAt();
        Thread.sleep(1);
        character.addExperience(10);
        assertEquals(10, character.getExperience());
        assertTrue(character.getUpdatedAt().isAfter(before));
    }

    @Test
    void shouldNotAllowNegativeExperience() {
        Character character = Character.create("Hero_", Job.WARRIOR);
        assertThrows(IllegalArgumentException.class, () -> character.addExperience(-5));
    }


    @Test
    void shouldSetNameAndUpdateTimestamp() throws InterruptedException {
        Character character = Character.create("Hero_", Job.WARRIOR);
        LocalDateTime before = character.getUpdatedAt();
        Thread.sleep(1);
        character.setName("NewName");
        assertEquals("NewName", character.getName());
        assertTrue(character.getUpdatedAt().isAfter(before));
    }


    @Test
    void shouldSetHealthAndUpdateTimestamp() throws InterruptedException {
        Character character = Character.create("Hero_", Job.WARRIOR);
        LocalDateTime before = character.getUpdatedAt();
        Thread.sleep(1);
        Health newHealth = new Health(5, 20);
        character.setHealth(newHealth);
        assertEquals(newHealth, character.getHealth());
        assertTrue(character.getUpdatedAt().isAfter(before));
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        Character c1 = Character.create("Hero_", Job.WARRIOR);
        Character c2 = new Character(c1.getId(), "Hero_", 1, Job.WARRIOR, c1.getStats(), c1.getHealth(), 0);
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void shouldTestToString() {
        Character character = Character.create("Hero_", Job.WARRIOR);
        String str = character.toString();
        assertTrue(str.contains("Character{"));
        assertTrue(str.contains("name='Hero_'"));
    }

    @Test
    void shouldBeAliveWhenHealthAboveZero() {
        Character character = Character.create("Hero_", Job.WARRIOR);
        assertTrue(character.isAlive());
    }

    @Test
    void shouldNotBeAliveWhenHealthZero() {
        Character character = Character.create("Hero_", Job.WARRIOR);
        character.setHealth(new Health(0, 20));
        assertFalse(character.isAlive());
    }
}
