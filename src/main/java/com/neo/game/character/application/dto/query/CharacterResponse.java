package com.neo.game.character.application.dto.query;

import com.neo.game.character.infrastructure.web.domain.model.enums.Job;

public class CharacterResponse {
    private String id;
    private String name;
    private int level;
    private Job job;
    private int health;
    private int maxHealth;
    private int experience;

    public CharacterResponse(String id, String name, int level, Job job, int health, int maxHealth, int experience) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.job = job;
        this.health = health;
        this.maxHealth = maxHealth;
        this.experience = experience;
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

    public Job getJob() {
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
}
