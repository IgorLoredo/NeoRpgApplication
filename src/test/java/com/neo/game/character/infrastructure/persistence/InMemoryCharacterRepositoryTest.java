package com.neo.game.character.infrastructure.persistence;

import com.neo.game.domain.model.Character;
import com.neo.game.domain.model.enums.Job;
import com.neo.game.domain.model.valueobjects.CharacterId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCharacterRepositoryTest {

    @Test
    void shouldSaveAndFindCharacter() {
        InMemoryCharacterRepository repo = new InMemoryCharacterRepository();
        Character character = Character.create("Hero_", Job.WARRIOR);

        repo.save(character);

        assertTrue(repo.findById(character.getId()).isPresent());
        assertTrue(repo.existsById(character.getId()));
    }

    @Test
    void shouldDeleteCharacter() {
        InMemoryCharacterRepository repo = new InMemoryCharacterRepository();
        Character character = Character.create("Hero_", Job.WARRIOR);
        repo.save(character);

        repo.delete(character.getId());

        assertFalse(repo.findById(character.getId()).isPresent());
        assertFalse(repo.existsById(character.getId()));
    }
}
