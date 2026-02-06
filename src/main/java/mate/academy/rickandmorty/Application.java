package mate.academy.rickandmorty;

import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.external.CharacterInputDto;
import mate.academy.rickandmorty.repisitory.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Profile("!test")
public class Application implements ApplicationRunner {

    private final CharacterClient characterClient;

    private final CharacterService characterService;

    private final CharacterRepository characterRepository;

    public Application(CharacterClient characterClient, CharacterService characterService, CharacterRepository characterRepository) {
        this.characterClient = characterClient;
        this.characterService = characterService;
        this.characterRepository = characterRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (characterRepository.count() > 0) {
            return;
        }

        String url = "https://rickandmortyapi.com/api/character/?page=1";
        List<CharacterExternalDto> characters = new ArrayList<>();

        while (url != null) {
            CharacterInputDto result = characterClient.getCharacter(url);

            url = result.getInfo().getNext();
            characters.addAll(result.getResults());

            Thread.sleep(200);
        }
        if (!characters.isEmpty()) {
            characterService.saveCharacters(characters);
        }
    }
}
