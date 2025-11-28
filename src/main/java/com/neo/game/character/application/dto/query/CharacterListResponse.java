package com.neo.game.character.application.dto.query;

import java.util.List;

public class CharacterListResponse {
    private List<CharacterResponse> characters;
    private int total;

    public CharacterListResponse(List<CharacterResponse> characters, int total) {
        this.characters = characters;
        this.total = total;
    }

    public List<CharacterResponse> getCharacters() {
        return characters;
    }

    public int getTotal() {
        return total;
    }
}
