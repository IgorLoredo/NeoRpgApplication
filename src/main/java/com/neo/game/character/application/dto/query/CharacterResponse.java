package com.neo.game.character.application.dto.query;

import com.neo.game.character.infrastructure.web.domain.model.enums.Job;

import java.time.LocalDateTime;

public class CharacterResponse {
    private final String id;
    private final String name;
    private final Job job;
    private final int level;
    private final int currentHealth;
    private final int maxHealth;
    private final boolean alive;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int attackModifier;
    private final int speedModifier;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CharacterResponse(String id, String name, Job job, int level, int currentHealth, int maxHealth, boolean alive,
                             int strength, int dexterity, int intelligence, int attackModifier, int speedModifier,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.level = level;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.alive = alive;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.attackModifier = attackModifier;
        this.speedModifier = speedModifier;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getSpeedModifier() {
        return speedModifier;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
