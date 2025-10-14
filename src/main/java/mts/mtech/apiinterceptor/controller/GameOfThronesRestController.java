package mts.mtech.apiinterceptor.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.gameofthrones.CharacterDetails;
import mts.mtech.apiinterceptor.dto.news.Response;
import mts.mtech.apiinterceptor.services.gameofthrones.CharacterService;
import mts.mtech.apiinterceptor.utils.Constants;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequestMapping("/v1/got")
@Tag(name = "Game Of Thrones APIs", description = "Game Of Thrones Api with characters from the world's popular show")
public class GameOfThronesRestController {
    /**
     * types of dependency injection in Spring Boot
     * Field injection: Dependencies are injected directly into fields using the @Autowired annotation.
     * Setter injection: Dependencies are injected via setter methods annotated with @Autowired.
     * Constructor injection: Dependencies are provided through the class constructor
     * */
    private final CharacterService characterService;

    public GameOfThronesRestController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public Response<List<CharacterDetails>> getCharacters(){
        log.debug("REST request to get characters from Game Of Thrones");
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
