package com.neo.game.character.infrastructure.web.mapper;

import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.infrastructure.web.dto.CharacterWebResponse;
import com.neo.game.domain.model.enums.Job;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CharacterWebMapperTest {

    @Test
    void shouldMapToWebResponse() {
        LocalDateTime now = LocalDateTime.now();
        CharacterResponse response = new CharacterResponse(
            "id-123",
            "Hero_",
            Job.WARRIOR,
            1,
            10,
            10,
            true,
            5,
            6,
            7,
            8,
            9,
            now,
            now
        );

        CharacterWebResponse web = CharacterWebMapper.toWeb(response);

        assertEquals("id-123", web.getId());
        assertEquals("Hero_", web.getName());
        assertEquals("WARRIOR", web.getJob());
        assertEquals(10, web.getCurrentHealth());
        assertEquals(9, web.getSpeedModifier());
        assertEquals(now, web.getCreatedAt());
    }
}
