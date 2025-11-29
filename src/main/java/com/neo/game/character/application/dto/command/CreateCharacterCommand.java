package com.neo.game.character.application.dto.command;

import com.neo.game.domain.model.enums.Job;

public class CreateCharacterCommand {
    private String name;
    private Job job;

    public CreateCharacterCommand() {
    }

    public CreateCharacterCommand(String name, Job job) {
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
