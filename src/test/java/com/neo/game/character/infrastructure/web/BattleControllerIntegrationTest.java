package com.neo.game.character.infrastructure.web;

import com.jayway.jsonpath.JsonPath;
import com.neo.game.GameCharacterApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GameCharacterApplication.class)
class BattleControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private String createCharacter(String name, String job) throws Exception {
        String response = mockMvc.perform(post("/api/v1/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"" + name + "\",\"job\":\"" + job + "\"}"))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();
        return JsonPath.read(response, "$.data.id");
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void battleEndpointReturnsLogAndWinner() throws Exception {
        String attackerId = createCharacter("Hero_", "WARRIOR");
        String defenderId = createCharacter("Mage__", "MAGE");

        String battleResponse = mockMvc.perform(post("/api/v1/battles")
                .param("attackerId", attackerId)
                .param("defenderId", defenderId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.winnerName").isNotEmpty())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var log = JsonPath.read(battleResponse, "$.data.log");
        assertThat(log).isInstanceOfAny(java.util.List.class);
        assertThat((java.util.List<?>) log).isNotEmpty();
    }

    @Test
    void charactersListReflectsAliveStatus() throws Exception {
        createCharacter("AliveA", "WARRIOR");
        createCharacter("AliveB", "THIEF");

        String listResponse = mockMvc.perform(get("/api/v1/characters"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.total").value(org.hamcrest.Matchers.greaterThanOrEqualTo(2)))
            .andReturn()
            .getResponse()
            .getContentAsString();

        var aliveFlags = JsonPath.read(listResponse, "$.data.characters[*].alive");
        assertThat((java.util.List<?>) aliveFlags).isNotEmpty();
        assertThat(aliveFlags.toString()).contains("true");
    }
}
