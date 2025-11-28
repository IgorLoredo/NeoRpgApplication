package com.neo.game.character.infrastructure.config;

import com.neo.game.character.application.ports.out.CharacterRepositoryPort;
import com.neo.game.character.application.service.BattleService;
import com.neo.game.character.application.service.CharacterService;
import com.neo.game.character.infrastructure.persistence.InMemoryCharacterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CharacterRepositoryPort characterRepository() {
        return new InMemoryCharacterRepository();
    }

    @Bean
    public CharacterService characterService(CharacterRepositoryPort repository) {
        return new CharacterService(repository);
    }

    @Bean
    public BattleService battleService(CharacterRepositoryPort repository) {
        return new BattleService(repository);
    }
}
