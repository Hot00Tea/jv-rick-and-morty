package mate.academy.rickandmorty.dto.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CharacterInputDto {

    private CharacterInfoDto info;

    private List<CharacterExternalDto> results;
}
