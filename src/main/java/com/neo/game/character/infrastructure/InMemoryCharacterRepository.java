package com.neo.game.character.infrastructure;

import com.neo.game.character.application.ports.CharacterRepository;
import com.neo.game.character.infrastructure.web.domain.GameCharacter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryCharacterRepository implements CharacterRepository {
    private final Map<UUID, GameCharacter> storage = new HashMap<>();

    @Override
    public GameCharacter save(GameCharacter character) {
        storage.put(character.getId(), character);
        return character;
    }

    @Override
    public Optional<GameCharacter> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }
}
