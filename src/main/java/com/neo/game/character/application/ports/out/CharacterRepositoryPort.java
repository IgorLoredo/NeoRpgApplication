package com.neo.game.character.application.ports.out;

import com.neo.game.domain.model.Character;
import com.neo.game.domain.model.valueobjects.CharacterId;

import java.util.List;
import java.util.Optional;

public interface CharacterRepositoryPort {
    Character save(Character character);
    Optional<Character> findById(CharacterId id);
    List<Character> findAll();
    void delete(CharacterId id);
    boolean existsById(CharacterId id);
}
