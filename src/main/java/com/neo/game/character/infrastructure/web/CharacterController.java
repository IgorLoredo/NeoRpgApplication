package com.neo.game.character.infrastructure.web;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.application.service.CharacterService;
import com.neo.game.character.infrastructure.web.dto.ApiResponse;
import com.neo.game.character.infrastructure.web.dto.CharacterWebResponse;
import com.neo.game.character.infrastructure.web.dto.CreateCharacterRequest;
import com.neo.game.character.infrastructure.web.dto.PagedResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CharacterWebResponse>> create(@Valid @RequestBody CreateCharacterRequest request) {
        CreateCharacterCommand command = new CreateCharacterCommand(request.getName(), request.getJob());
        CharacterResponse response = characterService.createCharacter(command);
        
        CharacterWebResponse webResponse = new CharacterWebResponse(
            response.getId(),
            response.getName(),
            response.getLevel(),
            response.getJob().name(),
            response.getHealth(),
            response.getMaxHealth(),
            response.getExperience(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(webResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CharacterWebResponse>> getById(@PathVariable String id) {
        CharacterResponse response = characterService.getCharacterById(id);
        
        CharacterWebResponse webResponse = new CharacterWebResponse(
            response.getId(),
            response.getName(),
            response.getLevel(),
            response.getJob().name(),
            response.getHealth(),
            response.getMaxHealth(),
            response.getExperience(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        
        return ResponseEntity.ok(ApiResponse.success(webResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<CharacterWebResponse>>> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        
        // Implementação simples de paginação em memória
        List<CharacterWebResponse> characters = new ArrayList<>(); // TODO: implementar paginação real
        
        PagedResponse<CharacterWebResponse> paged = new PagedResponse<>(
            characters,
            characters.size(),
            (characters.size() + pageSize - 1) / pageSize,
            page,
            pageSize
        );
        
        return ResponseEntity.ok(ApiResponse.success(paged));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        // TODO: implementar delete
        return ResponseEntity.noContent().build();
    }
}
