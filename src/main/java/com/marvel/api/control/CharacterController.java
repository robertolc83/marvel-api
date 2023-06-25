package com.marvel.api.control;

import com.marvel.api.model.entity.CharacterDataWrapper;
import com.marvel.api.model.entity.QueryLog;
import com.marvel.api.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
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
    public CharacterDataWrapper getById(@PathVariable("characterId") int characterId) throws IOException {
        return characterService.findById(characterId);
    }

    @PostMapping()
    public void addQueryCharacter(@RequestBody QueryLog queryLog) {
        characterService.addQueryCharacter(queryLog);
    }

    @GetMapping("/queryLog")
    public List<QueryLog> GetAllQueryLog() {
        return characterService.GetAllQueryLog();
    }

}
