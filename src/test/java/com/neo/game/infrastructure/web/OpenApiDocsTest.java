package com.neo.game.infrastructure.web;

import com.neo.game.character.infrastructure.config.OpenApiConfig;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenApiDocsTest {

    @Test
    void openApiBeanHasValidVersion() {
        OpenAPI openAPI = new OpenApiConfig().customOpenAPI();
        assertNotNull(openAPI.getOpenapi(), "OpenAPI version must be set");
        assertTrue(openAPI.getOpenapi().startsWith("3."), "OpenAPI version should start with 3.x");
    }
}
