package com.neo.game.character.infrastructure.web.domain;

import java.util.Objects;
import java.util.UUID;

public class GameCharacter {
    private UUID id;
    private String name;
    private int level;
    private String classType;

    public GameCharacter(UUID id, String name, int level, String classType) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.classType = classType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter that = (GameCharacter) o;
        return level == that.level && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(classType, that.classType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, classType);
    }
}
