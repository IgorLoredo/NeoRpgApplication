package com.neo.game.character.application.dto.query;

import java.util.List;

public class BattleResultResponse {
    private final String winnerName;
    private final String loserName;
    private final int winnerRemainingHp;
    private final List<String> log;

    public BattleResultResponse(String winnerName, String loserName, int winnerRemainingHp, List<String> log) {
        this.winnerName = winnerName;
        this.loserName = loserName;
        this.winnerRemainingHp = winnerRemainingHp;
        this.log = log;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getLoserName() {
        return loserName;
    }

    public int getWinnerRemainingHp() {
        return winnerRemainingHp;
    }

    public List<String> getLog() {
        return log;
    }
}
