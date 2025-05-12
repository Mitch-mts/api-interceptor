package mts.mtech.apiinterceptor.services.gameofthrones;

import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.gameofthrones.Continents;
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
public class ContinentsServiceImpl implements ContinentsService{
    @Autowired
    RestTemplate restTemplate;

    @Value("${api.game-of-thrones-continents}")
    private String GOT_CONTINENTS;

    @Override
    public List<Continents> getContinents() {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);

            log.info("httpEntity: {}", httpEntity);

            var response = restTemplate.exchange(
                    GOT_CONTINENTS,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<Continents>>() {
                    }
            ).getBody();

            log.info("response: {}", response);

            return response;

        }catch (Exception e){
            log.error("error: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to get Game Of Thrones continents");
        }
    }

    @Override
    public Continents getContinentById(Long id) {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);

            log.info("body: {}", httpEntity);

            return restTemplate.exchange(
                    GOT_CONTINENTS + "/" + id,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Continents>() {
                    }
            ).getBody();

        }catch (Exception e){
            log.error("error: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to get Game Of Thrones continents");
        }
    }
}
