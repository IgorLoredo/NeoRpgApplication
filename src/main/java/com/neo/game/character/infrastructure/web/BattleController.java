package com.neo.game.character.infrastructure.web;

import com.neo.game.character.application.dto.command.BattleCommand;
import com.neo.game.character.application.dto.query.BattleResultResponse;
import com.neo.game.character.application.service.BattleService;
import com.neo.game.character.infrastructure.web.domain.model.valueobjects.CharacterId;
import com.neo.game.character.infrastructure.web.dto.ApiResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/battles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BattleResultResponse>> executeBattle(
            @RequestParam @NotBlank(message = "Attacker ID cannot be blank") String attackerId,
            @RequestParam @NotBlank(message = "Defender ID cannot be blank") String defenderId,
            @RequestParam @Min(value = 1, message = "Damage must be at least 1") int damage) {

        try {
            BattleCommand command = new BattleCommand(
                new CharacterId(UUID.fromString(attackerId)),
                new CharacterId(UUID.fromString(defenderId)),
                damage
            );

            BattleResultResponse result = battleService.executeBattle(command);
            return ResponseEntity.ok(ApiResponse.success(result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }
}
