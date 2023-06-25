package com.marvel.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryLog {

    private String characterName;
    private int characterId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryLog queryLog = (QueryLog) o;
        return characterId == queryLog.characterId && Objects.equals(characterName, queryLog.characterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterName, characterId);
    }
}
