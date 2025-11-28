package com.neo.game.character.application.ports.in;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterResponse;

public interface CharacterUseCase {
    CharacterResponse createCharacter(CreateCharacterCommand command);
    CharacterResponse getCharacterById(String id);
}
