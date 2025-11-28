package com.neo.game.character.application;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.application.service.CharacterService;
import com.neo.game.character.infrastructure.web.domain.model.enums.Job;
import com.neo.game.character.infrastructure.persistence.InMemoryCharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterServiceTest {
    private CharacterService characterService;

    @BeforeEach
    public void setUp() {
        characterService = new CharacterService(new InMemoryCharacterRepository());
    }

    @Test
    public void shouldCreateCharacterSuccessfully() {
        CreateCharacterCommand command = new CreateCharacterCommand("Aragorn", Job.WARRIOR);
        CharacterResponse response = characterService.createCharacter(command);

        assertNotNull(response);
        assertEquals("Aragorn", response.getName());
        assertEquals(Job.WARRIOR, response.getJob());
        assertEquals(1, response.getLevel());
    }

    @Test
    public void shouldThrowExceptionForEmptyName() {
        CreateCharacterCommand command = new CreateCharacterCommand("", Job.MAGE);

        assertThrows(IllegalArgumentException.class, () -> characterService.createCharacter(command));
    }

    @Test
    public void shouldThrowExceptionForNullJob() {
        CreateCharacterCommand command = new CreateCharacterCommand("Frodo", null);

        assertThrows(IllegalArgumentException.class, () -> characterService.createCharacter(command));
    }
}
