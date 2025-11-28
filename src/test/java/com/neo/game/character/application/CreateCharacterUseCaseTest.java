package com.neo.game.character.application;

import com.neo.game.character.application.ports.CharacterRepository;
import com.neo.game.character.infrastructure.web.domain.GameCharacter;
import com.neo.game.character.infrastructure.InMemoryCharacterRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCharacterUseCaseTest {

    @Test
    public void createCharacter_shouldReturnSavedCharacter() {
        CharacterRepository repo = new InMemoryCharacterRepository();
        CreateCharacterUseCase useCase = new CreateCharacterUseCase(repo);

        GameCharacter created = useCase.execute("Hero", 5, "Warrior");

        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("Hero", created.getName());
        assertEquals(5, created.getLevel());
        assertEquals("Warrior", created.getClassType());

        // repository should return same
        assertTrue(repo.findById(created.getId()).isPresent());
    }
}
