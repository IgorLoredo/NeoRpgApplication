package com.neo.game.shared.random;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomProvider implements RandomProvider {
    @Override
    public int nextInt(int boundInclusive) {
        if (boundInclusive < 0) {
            throw new IllegalArgumentException("Bound must be non-negative");
        }
        return ThreadLocalRandom.current().nextInt(boundInclusive + 1);
    }
}
