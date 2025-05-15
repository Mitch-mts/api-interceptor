package mts.mtech.apiinterceptor.services.gameofthrones;

import mts.mtech.apiinterceptor.dto.gameofthrones.CharacterDetails;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CharacterService {
    List<CharacterDetails> getCharacters();
    CharacterDetails getCharacterById(Long id);
    List<CharacterDetails> getGOTDetails(Long characterId) throws ExecutionException, InterruptedException;
}
