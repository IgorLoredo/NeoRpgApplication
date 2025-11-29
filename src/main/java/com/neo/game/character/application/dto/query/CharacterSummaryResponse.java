package com.neo.game.character.application.dto.query;

import com.neo.game.domain.model.enums.Job;

public class CharacterSummaryResponse {
    private final String id;
    private final String name;
    private final Job job;
    private final boolean alive;

    public CharacterSummaryResponse(String id, String name, Job job, boolean alive) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.alive = alive;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }

    public boolean isAlive() {
        return alive;
    }
}
