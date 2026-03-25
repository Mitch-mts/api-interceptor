package mts.mtech.apiinterceptor.services.sources;

import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.news.Sources;
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
public class NewsSourcesServiceImpl implements NewsSourcesService{
    @Autowired
    RestTemplate restTemplate;

    @Value("${api-key.news}")
    private String NEWS_API_KEY;

    @Value("${api.news-sources}")
    private String NEWS_SOURCES;

    @Value("${api.news-base-url}")
    private String NEWS_BASE_URL;

    @Override
    public List<Sources> getSources() {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("X-Api-Key", NEWS_API_KEY);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);

            log.info("url: {}", NEWS_BASE_URL + NEWS_SOURCES);
            log.info("body: {}", httpEntity);

            var response = restTemplate.exchange(
                    NEWS_BASE_URL + NEWS_SOURCES,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<Sources>>() {
                    }
            ).getBody();

            log.info("response: {}", response);

            return response;

        }catch (Exception e){
            log.error("error: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to get News");
        }
    }
}
