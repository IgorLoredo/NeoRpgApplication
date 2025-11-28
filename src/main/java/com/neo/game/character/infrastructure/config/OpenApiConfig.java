package com.neo.game.character.infrastructure.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@Configuration
@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "Game Character API",
        version = "1.0.0",
        description = "API REST para gerenciamento de personagens de RPG",
        contact = @io.swagger.v3.oas.annotations.info.Contact(name = "NeoRpg Team", email = "devs@example.com"),
        license = @io.swagger.v3.oas.annotations.info.License(name = "MIT", url = "https://opensource.org/licenses/MIT")
    )
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .openapi("3.0.1")
                .info(new Info()
                        .title("Game Character API")
                        .version("1.0.0")
                        .description("API REST para gerenciamento de personagens de RPG")
                        .contact(new Contact().name("NeoRpg Team").email("devs@example.com"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
                );
    }
}
