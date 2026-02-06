package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mate.academy.rickandmorty.dto.internal.CharacterOutputDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Character management", description = "Endpoint for managing characters")
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Operation(summary = "Get random character", description = "Get random character")
    @GetMapping("/random")
    public CharacterOutputDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Get character by name", description = "Part of character name (case-insensitive)")
    @GetMapping
    public List<CharacterOutputDto> getCharacterByName(@RequestParam String name) {
        return characterService.getCharacterByName(name);
    }
}
