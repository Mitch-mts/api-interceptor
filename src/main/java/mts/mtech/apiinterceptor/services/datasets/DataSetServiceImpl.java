package mts.mtech.apiinterceptor.services.datasets;


import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.datasets.AthletesDto;
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

@Service
@Slf4j
public class DataSetServiceImpl implements DataSetService{
    private final RestTemplate restTemplate;

    @Value("${datasets.athletes}")
    private String ATHLETES_URL;

    public DataSetServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AthletesDto getAthletes() {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
            LoggingUtility.logWithContextAndLogLevel(DataSetServiceImpl.class, "getAthletes", "HTTP Entity for fetching athletes dataset", httpEntity, Level.DEBUG);

            return restTemplate.exchange(
                    ATHLETES_URL,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<AthletesDto>() {
                    }
            ).getBody();

        } catch (Exception e){
            LoggingUtility.logWithContextAndLogLevel(DataSetServiceImpl.class, "getAthletes", "Failed to retrieve dataset details", e, Level.ERROR);
            return AthletesDto.builder()
                    .count("0")
                    .message("Failed to fetch data from external API: " + e.getMessage())
                    .build();
        }
    }
}
