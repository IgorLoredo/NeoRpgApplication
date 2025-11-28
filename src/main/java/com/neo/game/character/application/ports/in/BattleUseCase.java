package com.neo.game.character.application.ports.in;

import com.neo.game.character.application.dto.command.BattleCommand;
import com.neo.game.character.application.dto.query.BattleResultResponse;

public interface BattleUseCase {
    BattleResultResponse executeBattle(BattleCommand command);
}
