package com.neo.game.character.infrastructure.web.dto;

import java.time.LocalDateTime;

public class CharacterWebResponse {
    private String id;
    private String name;
    private int level;
    private String job;
    private int health;
    private int maxHealth;
    private int experience;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CharacterWebResponse(String id, String name, int level, String job, int health, int maxHealth, int experience, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.job = job;
        this.health = health;
        this.maxHealth = maxHealth;
        this.experience = experience;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getJob() {
        return job;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getExperience() {
        return experience;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
