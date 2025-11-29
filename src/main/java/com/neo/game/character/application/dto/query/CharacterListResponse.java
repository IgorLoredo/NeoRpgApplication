package com.neo.game.character.application.dto.query;

import java.util.List;

public class CharacterListResponse {
    private final List<CharacterSummaryResponse> characters;
    private final int total;

    public CharacterListResponse(List<CharacterSummaryResponse> characters, int total) {
        this.characters = characters;
        this.total = total;
    }

    public List<CharacterSummaryResponse> getCharacters() {
        return characters;
    }

    public int getTotal() {
        return total;
    }
}
