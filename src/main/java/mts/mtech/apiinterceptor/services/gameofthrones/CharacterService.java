package mts.mtech.apiinterceptor.services.gameofthrones;

import mts.mtech.apiinterceptor.dto.gameofthrones.CharacterDetails;

import java.util.List;

public interface CharacterService {
    List<CharacterDetails> getCharacters();
    CharacterDetails getCharacterById(Long id);
}
