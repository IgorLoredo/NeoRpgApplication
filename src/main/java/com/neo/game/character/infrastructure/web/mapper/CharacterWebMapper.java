package com.neo.game.character.infrastructure.web.mapper;

import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.infrastructure.web.dto.CharacterWebResponse;

public final class CharacterWebMapper {

    private CharacterWebMapper() {
    }

    public static CharacterWebResponse toWeb(CharacterResponse response) {
        return new CharacterWebResponse(
            response.getId(),
            response.getName(),
            response.getJob().name(),
            response.getLevel(),
            response.getCurrentHealth(),
            response.getMaxHealth(),
            response.isAlive(),
            response.getStrength(),
            response.getDexterity(),
            response.getIntelligence(),
            response.getAttackModifier(),
            response.getSpeedModifier(),
            response.getCreatedAt(),
            response.getUpdatedAt()
        );
    }
}
