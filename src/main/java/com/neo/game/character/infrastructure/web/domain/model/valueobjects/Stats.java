package com.neo.game.character.infrastructure.web.domain.model.valueobjects;

import com.neo.game.character.infrastructure.web.domain.model.exceptions.DomainException;
import java.util.Objects;

public class Stats {
    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;

    public Stats(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        if (strength < 0 || dexterity < 0 || constitution < 0 || intelligence < 0 || wisdom < 0 || charisma < 0) {
            throw new DomainException("All stats must be non-negative");
        }
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getTotal() {
        return strength + dexterity + constitution + intelligence + wisdom + charisma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return strength == stats.strength && dexterity == stats.dexterity && constitution == stats.constitution && intelligence == stats.intelligence && wisdom == stats.wisdom && charisma == stats.charisma;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength, dexterity, constitution, intelligence, wisdom, charisma);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "str=" + strength +
                ", dex=" + dexterity +
                ", con=" + constitution +
                ", int=" + intelligence +
                ", wis=" + wisdom +
                ", cha=" + charisma +
                '}';
    }
}
