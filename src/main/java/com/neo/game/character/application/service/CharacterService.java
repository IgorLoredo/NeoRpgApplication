
package com.neo.game.character.application.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.application.mapper.CharacterMapper;
import com.neo.game.character.application.ports.in.CharacterUseCase;
import com.neo.game.character.application.ports.out.CharacterRepositoryPort;
import com.neo.game.character.infrastructure.web.domain.model.Character;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.CharacterId;

import java.util.UUID;

public class CharacterService implements CharacterUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CharacterService.class);
    private final CharacterRepositoryPort repository;

    public CharacterService(CharacterRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public CharacterResponse createCharacter(CreateCharacterCommand command) {
        logger.info("Criando personagem: name={}, job={}", command.getName(), command.getJob());
        if (command.getName() == null || command.getName().isBlank()) {
            logger.warn("Nome do personagem vazio ou nulo");
            throw new IllegalArgumentException("Character name cannot be empty");
        }
        if (command.getJob() == null) {
            logger.warn("Job do personagem nulo");
            throw new IllegalArgumentException("Job cannot be null");
        }

        Character character = Character.create(command.getName(), command.getJob());
        Character saved = repository.save(character);
        logger.debug("Personagem salvo: id={}", saved.getId());
        return CharacterMapper.toResponse(saved);
    }

    @Override
    public CharacterResponse getCharacterById(String id) {
        logger.info("Buscando personagem por id: {}", id);
        CharacterId characterId = new CharacterId(UUID.fromString(id));
        Character character = repository.findById(characterId)
            .orElseThrow(() -> {
                logger.error("Personagem não encontrado: id={}", id);
                return new IllegalArgumentException("Character not found with id: " + id);
            });
        logger.debug("Personagem encontrado: id={}", character.getId());
        return CharacterMapper.toResponse(character);
    }
}
