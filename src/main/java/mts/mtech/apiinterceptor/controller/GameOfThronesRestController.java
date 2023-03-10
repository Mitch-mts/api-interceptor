package mts.mtech.apiinterceptor.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.gameofthrones.CharacterDetails;
import mts.mtech.apiinterceptor.dto.news.Response;
import mts.mtech.apiinterceptor.services.gameofthrones.CharacterService;
import mts.mtech.apiinterceptor.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/got")
@Tag(name = "Game Of Thrones APIs", description = "Game Of Thrones Api with characters from the world's popular show")
@Slf4j
public class GameOfThronesRestController {
    private final CharacterService characterService;

    public GameOfThronesRestController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public Response<List<CharacterDetails>> getCharacters(){
        return new Response<List<CharacterDetails>>().buildSuccessResponse(Constants.SUCCESS, characterService.getCharacters());
    }

    @GetMapping("/characters/{id}")
    public Response<CharacterDetails> getCharacterById(@PathVariable Long id){
        return new Response<CharacterDetails>().buildSuccessResponse(Constants.SUCCESS, characterService.getCharacterById(id));
    }
}
