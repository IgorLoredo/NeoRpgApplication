package com.neo.game.character.infrastructure.config;

import com.neo.game.character.application.ports.in.BattleUseCase;
import com.neo.game.character.application.ports.in.CharacterUseCase;
import com.neo.game.character.application.ports.out.CharacterRepositoryPort;
import com.neo.game.character.application.service.BattleService;
import com.neo.game.character.application.service.CharacterService;
import com.neo.game.character.infrastructure.persistence.InMemoryCharacterRepository;
import com.neo.game.shared.random.RandomProvider;
import com.neo.game.shared.random.ThreadLocalRandomProvider;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CharacterRepositoryPort characterRepository() {
        return new InMemoryCharacterRepository();
    }

    @Bean
    public CharacterUseCase characterService(CharacterRepositoryPort repository, MeterRegistry meterRegistry) {
        return new CharacterService(repository, meterRegistry);
    }

    @Bean
    public BattleUseCase battleService(CharacterRepositoryPort repository, RandomProvider randomProvider, MeterRegistry meterRegistry) {
        return new BattleService(repository, randomProvider, meterRegistry);
    }

    @Bean
    public RandomProvider randomProvider() {
        return new ThreadLocalRandomProvider();
    }
}
