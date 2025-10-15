package mts.mtech.apiinterceptor.services.gameofthrones;

import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.config.FailureCounter;
import mts.mtech.apiinterceptor.dto.gameofthrones.CharacterDetails;
import mts.mtech.apiinterceptor.utils.LoggingUtility;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class CharacterServiceImpl  implements CharacterService{

    private final RestTemplate restTemplate;
    private final FailureCounter failureCounter;


    @Value("${api.game-of-thrones-characters}")
    private String GOT_CHARACTERS;

    public CharacterServiceImpl(RestTemplate restTemplate, FailureCounter failureCounter) {
        this.restTemplate = restTemplate;
        this.failureCounter = failureCounter;
    }


    public List<CharacterDetails> getGOTDetails(Long characterId) throws ExecutionException, InterruptedException {
        CompletableFuture<List<CharacterDetails>> future1 = new CompletableFuture<>();
        CompletableFuture<CharacterDetails> future2 = new CompletableFuture<>();

        future1 = CompletableFuture.supplyAsync(this::getCharacters);
        future2 = CompletableFuture.supplyAsync(() -> getCharacterById(characterId));
        log.info("future1:: {}", future1.get());
        log.info("future2:: {}", future2.get());

        CompletableFuture<Void> allCompleted = CompletableFuture.allOf(future1, future2);
        allCompleted.get();
        log.info("allCompleted:: {}", allCompleted.get());
        return future1.get();
    }
    @Override
    public List<CharacterDetails> getCharacters() {
        try{
            LoggingUtility.logWithContextAndLogLevel(CharacterServiceImpl.class, "getCharacters", "Fetching Game Of Thrones characters from external API", "", Level.INFO);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
            LoggingUtility.logWithContextAndLogLevel(CharacterServiceImpl.class, "getCharacters", "HTTP Entity for fetching Game Of Thrones characters", httpEntity, Level.DEBUG);

            var response = restTemplate.exchange(
                    GOT_CHARACTERS,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<CharacterDetails>>() {
                    }
            ).getBody();

            LoggingUtility.logWithContextAndLogLevel(CharacterServiceImpl.class, "getCharacters", "Successfully fetched Game Of Thrones characters from external API", response, Level.INFO);
            return response;

        } catch (Exception e){
            failureCounter.recordFailure();
            LoggingUtility.logWithContextAndLogLevel(CharacterServiceImpl.class, "getCharacters", "Error fetching Game Of Thrones characters from external API", e, Level.ERROR);
            String errormessage = "Failed to get Game Of Thrones characters";
            throw new RuntimeException(errormessage);
        }
    }

    @Override
    public CharacterDetails getCharacterById(Long id) {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
            log.info("body: {}", httpEntity);

            var response = restTemplate.exchange(
                    GOT_CHARACTERS + id,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<CharacterDetails>() {
                    }
            ).getBody();

            log.info("response: {}", response);
            return response;

        }catch (Exception e){
            log.error("Exception {}", e.getMessage());
            throw new RuntimeException("Failed to get Game Of Thrones character details");
        }
    }

}
