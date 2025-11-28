package com.neo.game.character.application.mapper;

import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.infrastructure.web.domain.model.Character;

public class CharacterMapper {
    public static CharacterResponse toResponse(Character character) {
        return new CharacterResponse(
            character.getId().toString(),
            character.getName(),
            character.getLevel(),
            character.getJob(),
            character.getHealth().getCurrent(),
            character.getHealth().getMaximum(),
            character.getExperience()
        );
    }
}
