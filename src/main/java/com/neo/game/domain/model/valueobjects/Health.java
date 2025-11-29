package com.neo.game.domain.model.valueobjects;

import com.neo.game.domain.model.exceptions.DomainException;
import java.util.Objects;

public class Health {
    private final int current;
    private final int maximum;

    public Health(int current, int maximum) {
        if (maximum <= 0) {
            throw new DomainException("Maximum health must be greater than 0");
        }
        if (current < 0 || current > maximum) {
            throw new DomainException("Current health must be between 0 and " + maximum);
        }
        this.current = current;
        this.maximum = maximum;
    }

    public int getCurrent() {
        return current;
    }

    public int getMaximum() {
        return maximum;
    }

    public Health heal(int amount) {
        if (amount < 0) {
            throw new DomainException("Heal amount cannot be negative");
        }
        int newCurrent = Math.min(current + amount, maximum);
        return new Health(newCurrent, maximum);
    }

    public Health takeDamage(int amount) {
        if (amount < 0) {
            throw new DomainException("Damage amount cannot be negative");
        }
        int newCurrent = Math.max(current - amount, 0);
        return new Health(newCurrent, maximum);
    }

    public boolean isAlive() {
        return current > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Health health = (Health) o;
        return current == health.current && maximum == health.maximum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, maximum);
    }
}
