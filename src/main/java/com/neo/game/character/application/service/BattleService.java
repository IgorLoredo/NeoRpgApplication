package com.neo.game.character.application.service;

import com.neo.game.character.application.dto.command.BattleCommand;
import com.neo.game.character.application.dto.query.BattleResultResponse;
import com.neo.game.character.application.ports.in.BattleUseCase;
import com.neo.game.character.application.ports.out.CharacterRepositoryPort;
import com.neo.game.domain.model.Character;
import com.neo.game.domain.model.valueobjects.Health;
import com.neo.game.shared.random.RandomProvider;
import com.neo.game.shared.exception.NotFoundException;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BattleService implements BattleUseCase {
    private static final Logger logger = LoggerFactory.getLogger(BattleService.class);
    private final CharacterRepositoryPort repository;
    private final RandomProvider randomProvider;
    private final Counter battlesCounter;
    private final Timer battleTimer;

    public BattleService(CharacterRepositoryPort repository, RandomProvider randomProvider, MeterRegistry meterRegistry) {
        this.repository = repository;
        this.randomProvider = randomProvider;
        this.battlesCounter = meterRegistry.counter("battles.executed.count");
        this.battleTimer = meterRegistry.timer("battles.execution.time");
    }

    @Override
    @Retry(name = "battle-service")
    public BattleResultResponse executeBattle(BattleCommand command) {
        return battleTimer.record(() -> runBattle(command));
    }

    private BattleResultResponse runBattle(BattleCommand command) {
        Character c1 = repository.findById(command.getAttackerId())
            .orElseThrow(() -> new NotFoundException("Attacker not found"));
        Character c2 = repository.findById(command.getDefenderId())
            .orElseThrow(() -> new NotFoundException("Defender not found"));

        logger.info("Starting battle: attacker={}, defender={}", c1.getName(), c2.getName());
        List<String> log = new ArrayList<>();
        log.add(String.format(
            "Battle between %s (%s) - %d HP and %s (%s) - %d HP begins!",
            c1.getName(), c1.getJob(), c1.getHealth().getCurrent(),
            c2.getName(), c2.getJob(), c2.getHealth().getCurrent()
        ));

        Character attacker = c1;
        Character defender = c2;

        while (attacker.getHealth().isAlive() && defender.getHealth().isAlive()) {
            int attackerSpeedRoll;
            int defenderSpeedRoll;
            do {
                attackerSpeedRoll = rollSpeed(attacker);
                defenderSpeedRoll = rollSpeed(defender);
            } while (attackerSpeedRoll == defenderSpeedRoll);

            if (defenderSpeedRoll > attackerSpeedRoll) {
                Character tmp = attacker;
                attacker = defender;
                defender = tmp;
                int tmpSpeed = attackerSpeedRoll;
                attackerSpeedRoll = defenderSpeedRoll;
                defenderSpeedRoll = tmpSpeed;
            }

            log.add(String.format(
                "%s %d speed was faster than %s %d speed and will begin this round.",
                attacker.getName(), attackerSpeedRoll, defender.getName(), defenderSpeedRoll
            ));

            // Attacker turn
            int damage = rollDamage(attacker);
            Health newHealth = defender.getHealth().takeDamage(damage);
            defender.setHealth(newHealth);
            log.add(String.format("%s attacks %s for %d, %s has %d HP remaining.",
                attacker.getName(), defender.getName(), damage, defender.getName(), newHealth.getCurrent()));
            if (!defender.isAlive()) {
                break;
            }

            // Defender turn
            int counterDamage = rollDamage(defender);
            Health attackerHealth = attacker.getHealth().takeDamage(counterDamage);
            attacker.setHealth(attackerHealth);
            log.add(String.format("%s attacks %s for %d, %s has %d HP remaining.",
                defender.getName(), attacker.getName(), counterDamage, attacker.getName(), attackerHealth.getCurrent()));

            // next round switches roles
        }

        Character winner = attacker.getHealth().isAlive() ? attacker : defender;
        Character loser = winner == attacker ? defender : attacker;

        repository.save(attacker);
        repository.save(defender);

        log.add(String.format("%s wins the battle! %s still has %d HP remaining",
            winner.getName(), winner.getName(), winner.getHealth().getCurrent()));
        logger.info("Battle finished: winner={}, loser={}, winnerHp={}", winner.getName(), loser.getName(), winner.getHealth().getCurrent());
        battlesCounter.increment();

        return new BattleResultResponse(
            winner.getName(),
            loser.getName(),
            winner.getHealth().getCurrent(),
            log,
            "Battle completed",
            200
        );
    }

    private int rollSpeed(Character character) {
        int modifier = Math.max(0, character.speedModifier());
        return randomProvider.nextInt(modifier);
    }

    private int rollDamage(Character character) {
        int modifier = Math.max(0, character.attackModifier());
        return randomProvider.nextInt(modifier);
    }
}
