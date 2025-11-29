package com.neo.game.character.application.mapper;

import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.domain.model.Character;
import com.neo.game.domain.model.enums.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterMapperTest {

    @Test
    void shouldMapDomainCharacterToResponse() {
        Character character = Character.create("Hero_", Job.WARRIOR);

        CharacterResponse response = CharacterMapper.toResponse(character);

        assertEquals(character.getId().toString(), response.getId());
        assertEquals("Hero_", response.getName());
        assertEquals(Job.WARRIOR, response.getJob());
        assertEquals(character.getHealth().getCurrent(), response.getCurrentHealth());
        assertEquals(character.attackModifier(), response.getAttackModifier());
        assertEquals(character.speedModifier(), response.getSpeedModifier());
        assertEquals(character.getCreatedAt(), response.getCreatedAt());
    }
}
