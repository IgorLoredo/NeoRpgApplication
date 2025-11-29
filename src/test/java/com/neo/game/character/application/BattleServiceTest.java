package com.neo.game.character.application;

import com.neo.game.character.application.dto.command.BattleCommand;
import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.BattleResultResponse;
import com.neo.game.character.application.service.BattleService;
import com.neo.game.character.application.service.CharacterService;
import com.neo.game.character.infrastructure.persistence.InMemoryCharacterRepository;
import com.neo.game.character.infrastructure.web.domain.model.enums.Job;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.CharacterId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BattleServiceTest {

    private CharacterService characterService;
    private BattleService battleService;

    @BeforeEach
    void setUp() {
        InMemoryCharacterRepository repo = new InMemoryCharacterRepository();
        characterService = new CharacterService(repo);
        battleService = new BattleService(repo);
    }

    @Test
    void battleShouldFinishWithWinnerAndLog() {
        var attacker = characterService.createCharacter(new CreateCharacterCommand("Hero_", Job.WARRIOR));
        var defender = characterService.createCharacter(new CreateCharacterCommand("Thief_", Job.THIEF));

        BattleResultResponse result = battleService.executeBattle(new BattleCommand(
            new CharacterId(UUID.fromString(attacker.getId())),
            new CharacterId(UUID.fromString(defender.getId()))
        ));

        assertNotNull(result.getWinnerName());
        assertNotNull(result.getLoserName());
        assertTrue(result.getWinnerRemainingHp() >= 0);
        assertFalse(result.getLog().isEmpty());
    }
}
