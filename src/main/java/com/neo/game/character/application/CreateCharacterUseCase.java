package com.neo.game.character.application;

import com.neo.game.character.application.ports.CharacterRepository;
import com.neo.game.character.infrastructure.web.domain.GameCharacter;

import java.util.UUID;

public class CreateCharacterUseCase {
    private final CharacterRepository repository;

    public CreateCharacterUseCase(CharacterRepository repository) {
        this.repository = repository;
    }

    public GameCharacter execute(String name, int level, String classType) {
        UUID id = UUID.randomUUID();
        GameCharacter character = new GameCharacter(id, name, level, classType);
        return repository.save(character);
    }
}
