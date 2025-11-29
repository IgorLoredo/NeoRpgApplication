package com.neo.game.character.application.dto.command;

import com.neo.game.domain.model.valueobjects.CharacterId;

public class BattleCommand {
    private CharacterId attackerId;
    private CharacterId defenderId;

    public BattleCommand() {
    }

    public BattleCommand(CharacterId attackerId, CharacterId defenderId) {
        this.attackerId = attackerId;
        this.defenderId = defenderId;
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
}
