package com.neo.game.character.application.dto.query;

public class BattleResultResponse {
    private String attackerName;
    private String defenderName;
    private int damageDealt;
    private int defenderHealthRemaining;
    private boolean defenderAlive;

    public BattleResultResponse(String attackerName, String defenderName, int damageDealt, int defenderHealthRemaining, boolean defenderAlive) {
        this.attackerName = attackerName;
        this.defenderName = defenderName;
        this.damageDealt = damageDealt;
        this.defenderHealthRemaining = defenderHealthRemaining;
        this.defenderAlive = defenderAlive;
    }

    public String getAttackerName() {
        return attackerName;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public int getDefenderHealthRemaining() {
        return defenderHealthRemaining;
    }

    public boolean isDefenderAlive() {
        return defenderAlive;
    }
}
