package com.neo.game.character.application.mapper;

import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.infrastructure.web.domain.model.Character;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.Stats;

public class CharacterMapper {
    public static CharacterResponse toResponse(Character character) {
        Stats stats = character.getStats();
        return new CharacterResponse(
            character.getId().toString(),
            character.getName(),
            character.getJob(),
            character.getLevel(),
            character.getHealth().getCurrent(),
            character.getHealth().getMaximum(),
            character.isAlive(),
            stats.getStrength(),
            stats.getDexterity(),
            stats.getIntelligence(),
            character.attackModifier(),
            character.speedModifier(),
            character.getCreatedAt(),
            character.getUpdatedAt()
        );
    }
}
