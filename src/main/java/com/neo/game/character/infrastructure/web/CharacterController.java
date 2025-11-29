package com.neo.game.character.infrastructure.web;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterListResponse;
import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.application.ports.in.CharacterUseCase;
import com.neo.game.character.infrastructure.web.dto.ApiResponse;
import com.neo.game.character.infrastructure.web.dto.CharacterWebResponse;
import com.neo.game.character.infrastructure.web.dto.CreateCharacterRequest;
import com.neo.game.character.infrastructure.web.mapper.CharacterWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/characters")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CharacterController {

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);
    private final CharacterUseCase characterUseCase;

    public CharacterController(CharacterUseCase characterUseCase) {
        this.characterUseCase = characterUseCase;
    }

    @PostMapping
    @Operation(
        summary = "Create a new character",
        description = "Creates an RPG character with name and job. Returns the created character.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                schema = @Schema(implementation = CreateCharacterRequest.class),
                examples = @ExampleObject(value = "{\"name\": \"Aragorn\", \"job\": \"WARRIOR\"}")
            )
        )
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Character created successfully",
            content = @Content(schema = @Schema(implementation = CharacterWebResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request or bad data",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<CharacterWebResponse>> create(@Valid @RequestBody CreateCharacterRequest request) {
        try {
            CreateCharacterCommand command = new CreateCharacterCommand(request.getName(), request.getJob());
            CharacterResponse response = characterUseCase.createCharacter(command);
            CharacterWebResponse webResponse = CharacterWebMapper.toWeb(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(webResponse));
        } catch (IllegalArgumentException ex) {
            logger.warn("Failed to create character: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "List characters", description = "Lists all characters with name, job, and alive/dead status.")
    public ResponseEntity<ApiResponse<CharacterListResponse>> list() {
        CharacterListResponse list = characterUseCase.listCharacters();
        return ResponseEntity.ok(ApiResponse.success(list));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get character by ID",
        description = "Returns a character by its identifier.",
        parameters = {
            @Parameter(name = "id", description = "Character ID", example = "b1a7e2c0-1234-4a5b-8c2d-1a2b3c4d5e6f")
        }
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Character found",
            content = @Content(schema = @Schema(implementation = CharacterWebResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid ID or character not found",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<CharacterWebResponse>> getById(@PathVariable String id) {
        try {
            CharacterResponse response = characterUseCase.getCharacterById(id);
            CharacterWebResponse webResponse = CharacterWebMapper.toWeb(response);
            return ResponseEntity.ok(ApiResponse.success(webResponse));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage()));
        }
    }
}
