package com.neo.game.character.infrastructure.web.domain.model.enums;

import com.neo.game.character.infrastructure.web.domain.model.valueobjects.BattleModifiers;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.Stats;

public enum Job {
    WARRIOR(
        new Stats(18, 10, 16, 8, 12, 13),
        new BattleModifiers(1.2, 1.1, 0.05)
    ),
    ROGUE(
        new Stats(12, 18, 12, 14, 11, 15),
        new BattleModifiers(1.0, 0.9, 0.25)
    ),
    MAGE(
        new Stats(8, 12, 10, 18, 16, 12),
        new BattleModifiers(1.5, 0.7, 0.10)
    ),
    PALADIN(
        new Stats(16, 12, 17, 13, 16, 16),
        new BattleModifiers(1.1, 1.3, 0.08)
    );

    private final Stats baseStats;
    private final BattleModifiers battleModifiers;

    Job(Stats baseStats, BattleModifiers battleModifiers) {
        this.baseStats = baseStats;
        this.battleModifiers = battleModifiers;
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    public BattleModifiers getBattleModifiers() {
        return battleModifiers;
    }

    public int getBaseHealth() {
        return 100 + (baseStats.getConstitution() * 5);
    }
}
