package com.neo.game.character.infrastructure.web.domain.model;

import com.neo.game.character.infrastructure.web.domain.model.enums.Job;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.CharacterId;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.Health;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.Stats;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
    private final CharacterId id;
    private String name;
    private int level;
    private Job job;
    private Stats stats;
    private Health health;
    private int experience;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Character(CharacterId id, String name, int level, Job job, Stats stats, Health health, int experience) {
        this.id = Objects.requireNonNull(id, "CharacterId cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.level = Math.max(1, level);
        this.job = Objects.requireNonNull(job, "Job cannot be null");
        this.stats = Objects.requireNonNull(stats, "Stats cannot be null");
        this.health = Objects.requireNonNull(health, "Health cannot be null");
        this.experience = Math.max(0, experience);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Character create(String name, Job job) {
        CharacterId id = CharacterId.generate();
        Stats baseStats = new Stats(job.getStrength(), job.getDexterity(), job.getIntelligence());
        Health health = new Health(job.getBaseHealth(), job.getBaseHealth());
        return new Character(id, name, 1, job, baseStats, health, 0);
    }

    public CharacterId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        this.level++;
        this.updatedAt = LocalDateTime.now();
    }

    public Job getJob() {
        return job;
    }

    public Stats getStats() {
        return stats;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = Objects.requireNonNull(health, "Health cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Experience amount cannot be negative");
        }
        this.experience += amount;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isAlive() {
        return health.isAlive();
    }

    public int attackModifier() {
        switch (job) {
            case WARRIOR:
                return (int) Math.round(0.8 * stats.getStrength() + 0.2 * stats.getDexterity());
            case THIEF:
                return (int) Math.round(0.25 * stats.getStrength() + 1.0 * stats.getDexterity() + 0.25 * stats.getIntelligence());
            case MAGE:
            default:
                return (int) Math.round(0.2 * stats.getStrength() + 0.2 * stats.getDexterity() + 1.2 * stats.getIntelligence());
        }
    }

    public int speedModifier() {
        switch (job) {
            case WARRIOR:
                return (int) Math.round(0.6 * stats.getDexterity() + 0.2 * stats.getIntelligence());
            case THIEF:
                return (int) Math.round(0.8 * stats.getDexterity());
            case MAGE:
            default:
                return (int) Math.round(0.4 * stats.getDexterity() + 0.1 * stats.getStrength());
        }
    }

    public int rollAttackDamage() {
        int modifier = Math.max(0, attackModifier());
        return ThreadLocalRandom.current().nextInt(modifier + 1);
    }

    public int rollSpeed() {
        int modifier = Math.max(0, speedModifier());
        return ThreadLocalRandom.current().nextInt(modifier + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", job=" + job +
                ", health=" + health +
                '}';
    }
}
