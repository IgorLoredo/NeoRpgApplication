package com.neo.game.character.infrastructure.web;

import com.neo.game.character.application.dto.command.CreateCharacterCommand;
import com.neo.game.character.application.dto.query.CharacterResponse;
import com.neo.game.character.application.service.CharacterService;
import com.neo.game.character.infrastructure.web.dto.ApiResponse;
import com.neo.game.character.infrastructure.web.dto.CharacterWebResponse;
import com.neo.game.character.infrastructure.web.dto.CreateCharacterRequest;
import com.neo.game.character.infrastructure.web.dto.PagedResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CharacterController {

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    @Operation(
        summary = "Cria um novo personagem",
        description = "Cria um personagem de RPG com nome e job. Retorna o personagem criado.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                schema = @Schema(implementation = CreateCharacterRequest.class),
                examples = @ExampleObject(value = "{\"name\": \"Aragorn\", \"job\": \"WARRIOR\"}")
            )
        )
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Personagem criado com sucesso",
            content = @Content(schema = @Schema(implementation = CharacterWebResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisicao invalida ou dados incorretos",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<CharacterWebResponse>> create(@Valid @RequestBody CreateCharacterRequest request) {
        logger.info("[POST] Criando personagem: name={}, job={}", request.getName(), request.getJob());
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
        logger.debug("Personagem criado com sucesso: id={}", webResponse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(webResponse));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Busca personagem por ID",
        description = "Retorna os dados de um personagem pelo seu identificador unico.",
        parameters = {
            @Parameter(name = "id", description = "ID do personagem", example = "b1a7e2c0-1234-4a5b-8c2d-1a2b3c4d5e6f")
        }
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Personagem encontrado",
            content = @Content(schema = @Schema(implementation = CharacterWebResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "ID invalido ou personagem nao encontrado",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<CharacterWebResponse>> getById(@PathVariable String id) {
        logger.info("[GET] Buscando personagem por id: {}", id);
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
        logger.debug("Personagem encontrado: id={}", webResponse.getId());
        return ResponseEntity.ok(ApiResponse.success(webResponse));
    }

    @GetMapping
    @Operation(
        summary = "Lista personagens paginados",
        description = "Retorna uma lista paginada de personagens.",
        parameters = {
            @Parameter(name = "page", description = "Numero da pagina (inicia em 0)", example = "0"),
            @Parameter(name = "pageSize", description = "Tamanho da pagina", example = "10")
        }
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de personagens",
            content = @Content(schema = @Schema(implementation = PagedResponse.class)))
    })
    public ResponseEntity<ApiResponse<PagedResponse<CharacterWebResponse>>> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        logger.info("[GET] Listando personagens: page={}, pageSize={}", page, pageSize);
        // Implementacao simples de paginacao em memoria
        List<CharacterWebResponse> characters = new ArrayList<>(); // TODO: implementar paginacao real
        PagedResponse<CharacterWebResponse> paged = new PagedResponse<>(
            characters,
            characters.size(),
            (characters.size() + pageSize - 1) / pageSize,
            page,
            pageSize
        );
        logger.debug("Total de personagens retornados: {}", characters.size());
        return ResponseEntity.ok(ApiResponse.success(paged));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remove personagem por ID",
        description = "Remove um personagem do sistema pelo seu identificador unico.",
        parameters = {
            @Parameter(name = "id", description = "ID do personagem", example = "b1a7e2c0-1234-4a5b-8c2d-1a2b3c4d5e6f")
        }
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Personagem removido com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "ID invalido ou personagem nao encontrado",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("[DELETE] Removendo personagem id={}", id);
        // TODO: implementar delete
        return ResponseEntity.noContent().build();
    }
}
