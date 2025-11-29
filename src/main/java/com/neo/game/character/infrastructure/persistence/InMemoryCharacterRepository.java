package com.neo.game.character.infrastructure.persistence;

import com.neo.game.character.application.ports.out.CharacterRepositoryPort;
import com.neo.game.domain.model.Character;
import com.neo.game.domain.model.valueobjects.CharacterId;

import java.util.*;

public class InMemoryCharacterRepository implements CharacterRepositoryPort {
    private final Map<CharacterId, Character> storage = new HashMap<>();

    @Override
    public Character save(Character character) {
        storage.put(character.getId(), character);
        return character;
    }

    @Override
    public Optional<Character> findById(CharacterId id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Character> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(CharacterId id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(CharacterId id) {
        return storage.containsKey(id);
    }
}
