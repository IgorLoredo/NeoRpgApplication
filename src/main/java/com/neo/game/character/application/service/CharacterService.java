package com.neo.game.character.application.service;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.application.mapper.CharacterMapper;
import com.neo.game.character.application.ports.in.CharacterUseCase;
import com.neo.game.character.application.ports.out.CharacterRepositoryPort;
import com.neo.game.character.infrastructure.web.domain.model.Character;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.CharacterId;

import java.util.UUID;

public class CharacterService implements CharacterUseCase {
    private final CharacterRepositoryPort repository;

    public CharacterService(CharacterRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public CharacterResponse createCharacter(CreateCharacterCommand command) {
        if (command.getName() == null || command.getName().isBlank()) {
            throw new IllegalArgumentException("Character name cannot be empty");
        }
        if (command.getJob() == null) {
            throw new IllegalArgumentException("Job cannot be null");
        }

        Character character = Character.create(command.getName(), command.getJob());
        Character saved = repository.save(character);
        return CharacterMapper.toResponse(saved);
    }

    @Override
    public CharacterResponse getCharacterById(String id) {
        CharacterId characterId = new CharacterId(UUID.fromString(id));
        Character character = repository.findById(characterId)
            .orElseThrow(() -> new IllegalArgumentException("Character not found with id: " + id));
        return CharacterMapper.toResponse(character);
    }
}
