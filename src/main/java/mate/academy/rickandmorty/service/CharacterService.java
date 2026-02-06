package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.internal.CharacterOutputDto;
import mate.academy.rickandmorty.repisitory.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import mate.academy.rickandmorty.model.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public void saveCharacters(List<CharacterExternalDto> characters) {
        List<Character> characterList = new ArrayList<>();

        for (CharacterExternalDto dto : characters) {
            Character saveCharacter = new Character();
            saveCharacter.setExternalId(dto.getId());
            saveCharacter.setName(dto.getName());
            saveCharacter.setStatus(dto.getStatus());
            saveCharacter.setGender(dto.getGender());
            characterList.add(saveCharacter);
        }
        characterRepository.saveAll(characterList);
    }

    public CharacterOutputDto getRandomCharacter() {
        long totalCharacters = characterRepository.count();
        Random random = new Random();
        int index = random.nextInt(0, (int) totalCharacters);

        PageRequest pageRequest = PageRequest.of(index, 1);
        Page<Character> characterPage = characterRepository.findAll(pageRequest);

        Character character = characterPage.getContent().get(0);

        CharacterOutputDto characterOutputDto = new CharacterOutputDto();
        characterOutputDto.setId(character.getId());
        characterOutputDto.setExternalId(character.getExternalId());
        characterOutputDto.setName(character.getName());
        characterOutputDto.setStatus(character.getStatus());
        characterOutputDto.setGender(character.getGender());

        return characterOutputDto;
    }

    public List<CharacterOutputDto> getCharacterByName(String name) {
        List<Character> characterList = characterRepository.findByNameContainingIgnoreCase(name);

        List<CharacterOutputDto> outputDtoList = new ArrayList<>();

        for (Character character : characterList) {
            CharacterOutputDto characterByName = new CharacterOutputDto();
            characterByName.setExternalId(character.getExternalId());
            characterByName.setName(character.getName());
            characterByName.setStatus(character.getStatus());
            characterByName.setGender(character.getGender());
            outputDtoList.add(characterByName);
        }
        return outputDtoList;
    }
}
