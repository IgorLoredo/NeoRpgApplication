package com.neo.game.domain.model.valueobjects;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CharacterIdTest {
    @Test
    void shouldGenerateUniqueIds() {
        CharacterId id1 = CharacterId.generate();
        CharacterId id2 = CharacterId.generate();
        assertNotEquals(id1, id2);
        assertNotEquals(id1.getValue(), id2.getValue());
    }

    @Test
    void shouldAcceptValidUuid() {
        UUID uuid = UUID.randomUUID();
        CharacterId id = new CharacterId(uuid);
        assertEquals(uuid, id.getValue());
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        UUID uuid = UUID.randomUUID();
        CharacterId id1 = new CharacterId(uuid);
        CharacterId id2 = new CharacterId(uuid);
        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void shouldTestToString() {
        CharacterId id = CharacterId.generate();
        assertEquals(id.getValue().toString(), id.toString());
    }
}
