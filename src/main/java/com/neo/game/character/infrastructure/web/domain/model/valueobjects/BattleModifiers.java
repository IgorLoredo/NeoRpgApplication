package com.neo.game.character.infrastructure.web.domain.model.valueobjects;

import java.util.Objects;

public class BattleModifiers {
    private final double damageMultiplier;
    private final double defensiveMultiplier;
    private final double criticalChance;

    public BattleModifiers(double damageMultiplier, double defensiveMultiplier, double criticalChance) {
        if (damageMultiplier < 0 || defensiveMultiplier < 0 || criticalChance < 0 || criticalChance > 1) {
            throw new IllegalArgumentException("Invalid battle modifiers");
        }
        this.damageMultiplier = damageMultiplier;
        this.defensiveMultiplier = defensiveMultiplier;
        this.criticalChance = criticalChance;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public double getDefensiveMultiplier() {
        return defensiveMultiplier;
    }

    public double getCriticalChance() {
        return criticalChance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleModifiers that = (BattleModifiers) o;
        return Double.compare(that.damageMultiplier, damageMultiplier) == 0 && Double.compare(that.defensiveMultiplier, defensiveMultiplier) == 0 && Double.compare(that.criticalChance, criticalChance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(damageMultiplier, defensiveMultiplier, criticalChance);
    }

    @Override
    public String toString() {
        return "BattleModifiers{" +
                "dmg=" + damageMultiplier +
                ", def=" + defensiveMultiplier +
                ", crit=" + criticalChance +
                '}';
    }
}
