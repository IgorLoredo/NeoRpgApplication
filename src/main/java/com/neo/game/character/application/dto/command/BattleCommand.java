package com.neo.game.character.application.dto.command;

import com.neo.game.character.infrastructure.web.domain.model.valueobjects.CharacterId;

public class BattleCommand {
    private CharacterId attackerId;
    private CharacterId defenderId;
    private int damage;

    public BattleCommand() {
    }

    public BattleCommand(CharacterId attackerId, CharacterId defenderId, int damage) {
        this.attackerId = attackerId;
        this.defenderId = defenderId;
        this.damage = damage;
    }

    public CharacterId getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(CharacterId attackerId) {
        this.attackerId = attackerId;
    }

    public CharacterId getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(CharacterId defenderId) {
        this.defenderId = defenderId;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
