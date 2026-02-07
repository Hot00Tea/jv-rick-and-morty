package mate.academy.rickandmorty.dto.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CharacterInfoDto {

    private Integer count;

    private Integer pages;

    private String next;

    private String prev;
}
