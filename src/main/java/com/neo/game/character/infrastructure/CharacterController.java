package com.neo.game.character.infrastructure;

import com.neo.game.character.application.CreateCharacterUseCase;
import com.neo.game.character.infrastructure.web.domain.GameCharacter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CreateCharacterUseCase createCharacterUseCase;
    private final InMemoryCharacterRepository repository;

    public CharacterController() {
        this.repository = new InMemoryCharacterRepository();
        this.createCharacterUseCase = new CreateCharacterUseCase(repository);
    }

    @PostMapping
    public ResponseEntity<GameCharacter> create(@RequestBody Map<String, Object> body) {
        String name = (String) body.getOrDefault("name", "Unnamed");
        int level = (int) (body.getOrDefault("level", 1) instanceof Integer ? body.getOrDefault("level", 1) : ((Number)body.getOrDefault("level", 1)).intValue());
        String classType = (String) body.getOrDefault("classType", "Adventurer");

        GameCharacter created = createCharacterUseCase.execute(name, level, classType);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameCharacter> getById(@PathVariable("id") UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
