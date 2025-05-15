package mts.mtech.apiinterceptor.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mts.mtech.apiinterceptor.dto.gameofthrones.CharacterDetails;
import mts.mtech.apiinterceptor.dto.news.Response;
import mts.mtech.apiinterceptor.services.gameofthrones.CharacterService;
import mts.mtech.apiinterceptor.utils.Constants;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/v1/got")
@Tag(name = "Game Of Thrones APIs", description = "Game Of Thrones Api with characters from the world's popular show")
public class GameOfThronesRestController {
    private final CharacterService characterService;

    public GameOfThronesRestController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public Response<List<CharacterDetails>> getCharacters(){
        return new Response<List<CharacterDetails>>()
                .buildSuccessResponse(Constants.SUCCESS, characterService.getCharacters());
    }

    @GetMapping("/characters/{id}")
    public Response<CharacterDetails> getCharacterById(@PathVariable Long id){
        return new Response<CharacterDetails>()
                .buildSuccessResponse(Constants.SUCCESS, characterService.getCharacterById(id));
    }

    @GetMapping("/character-details/{id}")
    public Response<?> getGameOfThronesDetails(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return new Response<>().buildSuccessResponse("SUCCESS", characterService.getGOTDetails(id));
    }
}
