package com.neo.game.character.application.ports;

import com.neo.game.character.infrastructure.web.domain.GameCharacter;

import java.util.Optional;
import java.util.UUID;

public interface CharacterRepository {
    GameCharacter save(GameCharacter character);
    Optional<GameCharacter> findById(UUID id);
}
