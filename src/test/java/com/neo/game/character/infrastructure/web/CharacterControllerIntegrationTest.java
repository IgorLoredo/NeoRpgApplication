package com.neo.game.character.infrastructure.web;

import com.neo.game.character.domain.model.enums.Job;
import com.neo.game.GameCharacterApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = GameCharacterApplication.class)
@AutoConfigureMockMvc
public class CharacterControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateCharacterSuccess() throws Exception {
        String requestBody = "{\"name\":\"Aragorn\",\"job\":\"WARRIOR\"}";

        mockMvc.perform(post("/api/v1/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Aragorn"))
                .andExpect(jsonPath("$.data.job").value("WARRIOR"))
                .andExpect(jsonPath("$.data.level").value(1));
    }

    @Test
    public void testCreateCharacterWithValidationError() throws Exception {
        String requestBody = "{\"name\":\"\",\"job\":\"WARRIOR\"}";

        mockMvc.perform(post("/api/v1/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void testCreateCharacterWithoutJob() throws Exception {
        String requestBody = "{\"name\":\"Frodo\"}";

        mockMvc.perform(post("/api/v1/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCharacterById() throws Exception {
        // Criar um personagem primeiro
        String createRequestBody = "{\"name\":\"Legolas\",\"job\":\"ROGUE\"}";
        
        String response = mockMvc.perform(post("/api/v1/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestBody))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Extrair ID (simplificado - em produção usar JsonPath)
        String characterId = response.substring(response.indexOf("\"id\":\"") + 6);
        characterId = characterId.substring(0, characterId.indexOf("\""));

        // Buscar o personagem
        mockMvc.perform(get("/api/v1/characters/" + characterId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Legolas"));
    }

    @Test
    public void testGetCharacterNotFound() throws Exception {
        String invalidId = "00000000-0000-0000-0000-000000000000";

        mockMvc.perform(get("/api/v1/characters/" + invalidId))
                .andExpect(status().isBadRequest());
    }
}
