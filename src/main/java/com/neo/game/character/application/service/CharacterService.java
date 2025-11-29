package com.neo.game.character.application.service;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterListResponse;
import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.application.dto.query.CharacterSummaryResponse;
import com.neo.game.character.application.mapper.CharacterMapper;
import com.neo.game.character.application.ports.in.CharacterUseCase;
import com.neo.game.character.application.ports.out.CharacterRepositoryPort;
import com.neo.game.character.infrastructure.web.domain.model.Character;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.CharacterId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CharacterService implements CharacterUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CharacterService.class);
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z_]{4,15}$");

    private final CharacterRepositoryPort repository;

    public CharacterService(CharacterRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public CharacterResponse createCharacter(CreateCharacterCommand command) {
        logger.info("Creating character: name={}, job={}", command.getName(), command.getJob());
        validateCreate(command);

        Character character = Character.create(command.getName(), command.getJob());
        Character saved = repository.save(character);
        logger.debug("Character persisted: id={}, job={}, hp={}", saved.getId(), saved.getJob(), saved.getHealth());
        return CharacterMapper.toResponse(saved);
    }

    @Override
    public CharacterResponse getCharacterById(String id) {
        logger.info("Fetching character by id={}", id);
        CharacterId characterId = new CharacterId(UUID.fromString(id));
        Character character = repository.findById(characterId)
            .orElseThrow(() -> {
                logger.error("Character not found: id={}", id);
                return new IllegalArgumentException("Character not found with id: " + id);
            });
        logger.debug("Character found: id={}, job={}, hp={}", character.getId(), character.getJob(), character.getHealth());
        return CharacterMapper.toResponse(character);
    }

    public CharacterListResponse listCharacters() {
        List<CharacterSummaryResponse> summaries = repository.findAll().stream()
            .map(ch -> new CharacterSummaryResponse(
                ch.getId().toString(),
                ch.getName(),
                ch.getJob(),
                ch.isAlive()
            ))
            .collect(Collectors.toList());
        return new CharacterListResponse(summaries, summaries.size());
    }

    private void validateCreate(CreateCharacterCommand command) {
        String name = command.getName();
        if (name == null || name.isBlank()) {
            logger.warn("Validation failed: empty name");
            throw new IllegalArgumentException("Character name cannot be empty");
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Name must be 4-15 chars, letters or underscores only");
        }
        if (command.getJob() == null) {
            logger.warn("Validation failed: null job");
            throw new IllegalArgumentException("Job must be one of WARRIOR, THIEF or MAGE");
        }
    }
}
