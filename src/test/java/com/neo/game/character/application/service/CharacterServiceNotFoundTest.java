package com.neo.game.character.application.service;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.infrastructure.persistence.InMemoryCharacterRepository;
import com.neo.game.domain.model.enums.Job;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CharacterServiceNotFoundTest {

    @Test
    void getCharacterByIdShouldThrowWhenNotFound() {
        CharacterService service = new CharacterService(new InMemoryCharacterRepository(), new SimpleMeterRegistry());
        // create one to ensure repo works
        service.createCharacter(new CreateCharacterCommand("Hero_", Job.WARRIOR));
        // random id not present
        assertThrows(IllegalArgumentException.class, () -> service.getCharacterById("00000000-0000-0000-0000-000000000000"));
    }
}
