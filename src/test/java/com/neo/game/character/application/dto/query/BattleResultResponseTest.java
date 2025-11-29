package com.neo.game.character.application.dto.query;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattleResultResponseTest {

    @Test
    void gettersShouldReturnValues() {
        List<String> log = List.of("Battle starts", "Hero wins");
        BattleResultResponse response = new BattleResultResponse("Hero", "Mage", 5, log, "Battle completed", 200);

        assertEquals("Hero", response.getWinnerName());
        assertEquals("Mage", response.getLoserName());
        assertEquals(5, response.getWinnerRemainingHp());
        assertEquals(log, response.getLog());
        assertEquals("Battle completed", response.getMessage());
        assertEquals(200, response.getStatusCode());
    }
}
