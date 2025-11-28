package com.neo.game.character.application.service;

import com.neo.game.character.application.dto.command.BattleCommand;
import com.neo.game.character.application.dto.query.BattleResultResponse;
import com.neo.game.character.application.ports.in.BattleUseCase;
import com.neo.game.character.application.ports.out.CharacterRepositoryPort;
import com.neo.game.character.infrastructure.web.domain.model.Character;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.Health;

public class BattleService implements BattleUseCase {
    private final CharacterRepositoryPort repository;

    public BattleService(CharacterRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public BattleResultResponse executeBattle(BattleCommand command) {
        Character attacker = repository.findById(command.getAttackerId())
            .orElseThrow(() -> new IllegalArgumentException("Attacker not found"));

        Character defender = repository.findById(command.getDefenderId())
            .orElseThrow(() -> new IllegalArgumentException("Defender not found"));

        // Apply damage with battle modifiers
        double finalDamage = command.getDamage() * attacker.getJob().getBattleModifiers().getDamageMultiplier();
        finalDamage = finalDamage / defender.getJob().getBattleModifiers().getDefensiveMultiplier();

        Health newHealth = defender.getHealth().takeDamage((int) finalDamage);
        defender.setHealth(newHealth);
        repository.save(defender);

        return new BattleResultResponse(
            attacker.getName(),
            defender.getName(),
            (int) finalDamage,
            newHealth.getCurrent(),
            newHealth.isAlive()
        );
    }
}
