package com.marvel.api.control;

import com.marvel.api.model.entity.CharacterDataWrapper;
import com.marvel.api.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/character")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping()
    public CharacterDataWrapper getAll() throws IOException {
        return characterService.findAll();
    }

    @GetMapping("/{characterId}")
    public CharacterDataWrapper getAll(@PathVariable("characterId") int characterId) throws IOException {
        return characterService.findById(characterId);
    }

}
