package com.neo.game.character.application;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.application.service.CharacterService;
import com.neo.game.character.infrastructure.persistence.InMemoryCharacterRepository;
import com.neo.game.character.infrastructure.web.domain.model.enums.Job;
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
        assertTrue(response.isAlive());
        assertEquals(20, response.getMaxHealth()); // base health for warrior
    }

    @Test
    public void shouldThrowExceptionForInvalidNamePattern() {
        CreateCharacterCommand command = new CreateCharacterCommand("a!", Job.MAGE);
        assertThrows(IllegalArgumentException.class, () -> characterService.createCharacter(command));
    }

    @Test
    public void shouldThrowExceptionForNullJob() {
        CreateCharacterCommand command = new CreateCharacterCommand("Frodo", null);
        assertThrows(IllegalArgumentException.class, () -> characterService.createCharacter(command));
    }

    @Test
    public void shouldListAllCreatedCharactersWithAliveStatus() {
        characterService.createCharacter(new CreateCharacterCommand("Hero_", Job.WARRIOR));
        characterService.createCharacter(new CreateCharacterCommand("Rogue_", Job.THIEF));

        var list = characterService.listCharacters();

        assertEquals(2, list.getTotal());
        assertEquals(2, list.getCharacters().size());
        assertTrue(list.getCharacters().stream().allMatch(resp -> resp.isAlive()));
    }
}
