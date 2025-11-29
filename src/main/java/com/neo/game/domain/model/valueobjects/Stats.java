package com.neo.game.domain.model.valueobjects;

import com.neo.game.domain.model.exceptions.DomainException;
import java.util.Objects;

public class Stats {
    private final int strength;
    private final int dexterity;
    private final int intelligence;

    public Stats(int strength, int dexterity, int intelligence) {
        if (strength < 0 || dexterity < 0 || intelligence < 0) {
            throw new DomainException("All stats must be non-negative");
        }
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return strength == stats.strength && dexterity == stats.dexterity && intelligence == stats.intelligence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength, dexterity, intelligence);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "str=" + strength +
                ", dex=" + dexterity +
                ", int=" + intelligence +
                '}';
    }
}
