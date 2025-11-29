package com.neo.game.shared.random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreadLocalRandomProviderTest {

    @Test
    void shouldGenerateValueWithinBounds() {
        ThreadLocalRandomProvider provider = new ThreadLocalRandomProvider();
        int bound = 5;
        int value = provider.nextInt(bound);
        assertTrue(value >= 0 && value <= bound);
    }

    @Test
    void shouldRejectNegativeBound() {
        ThreadLocalRandomProvider provider = new ThreadLocalRandomProvider();
        assertThrows(IllegalArgumentException.class, () -> provider.nextInt(-1));
    }
}
