package com.neo.game.character.infrastructure.web.dto;

import com.neo.game.domain.model.enums.Job;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateCharacterRequest {
    @NotBlank(message = "Character name cannot be blank")
    @Pattern(regexp = "^[A-Za-z_]{4,15}$", message = "Name must be 4-15 chars, letters or underscores only")
    private String name;

    @NotNull(message = "Job cannot be null")
    private Job job;

    public CreateCharacterRequest() {
    }

    public CreateCharacterRequest(String name, Job job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
