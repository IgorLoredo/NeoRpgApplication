package com.neo.game.domain.model.enums;

public enum Job {
    WARRIOR(20, 10, 5, 5),
    THIEF(15, 4, 10, 4),
    MAGE(12, 5, 6, 10);

    private final int baseHealth;
    private final int strength;
    private final int dexterity;
    private final int intelligence;

    Job(int baseHealth, int strength, int dexterity, int intelligence) {
        this.baseHealth = baseHealth;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getBaseHealth() {
        return baseHealth;
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
}
