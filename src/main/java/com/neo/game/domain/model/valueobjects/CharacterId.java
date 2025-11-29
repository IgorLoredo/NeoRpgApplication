package com.neo.game.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class CharacterId {
    private final UUID value;

    public CharacterId(UUID value) {
        Objects.requireNonNull(value, "CharacterId cannot be null");
        this.value = value;
    }

    public static CharacterId generate() {
        return new CharacterId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterId that = (CharacterId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
