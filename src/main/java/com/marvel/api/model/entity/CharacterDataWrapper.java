package com.marvel.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDataWrapper {

    private CharacterDataContainer data;

    public class CharacterDataContainer {
        public List<Character> results;
    }

    public class Character {
        public int id;
        public String name;
        public String description;
        public Image thumbnail;
    }

    public class Image {
        public String path;
        public String extension;
    }
}
