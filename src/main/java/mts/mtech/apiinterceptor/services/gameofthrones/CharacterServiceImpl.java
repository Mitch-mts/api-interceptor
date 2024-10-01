package mts.mtech.apiinterceptor.services.gameofthrones;

import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.gameofthrones.CharacterDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class CharacterServiceImpl  implements CharacterService{

    private final RestTemplate restTemplate;

    @Value("${api.game-of-thrones-characters}")
    private String GOT_CHARACTERS;

    public CharacterServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CharacterDetails> getCharacters() {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
            log.info("httpEntity: {}", httpEntity);

            var response = restTemplate.exchange(
                    GOT_CHARACTERS,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<CharacterDetails>>() {
                    }
            ).getBody();

            log.info("response: {}", response);
            return response;

        }catch (Exception e){
            log.error("GOT characters Exception: {}", e.getMessage());
            String errormessage = "Failed to get GOT characters";
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
            throw new RuntimeException("Failed to get GOT character details");
        }
    }

}
