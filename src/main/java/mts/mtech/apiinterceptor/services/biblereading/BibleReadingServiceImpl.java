package mts.mtech.apiinterceptor.services.biblereading;

import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.biblereading.BibleReadingDto;
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
public class BibleReadingServiceImpl implements BibleReadingService{

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.bible-reading}")
    private String BIBLE_READING;

    @Value("${api.book}")
    private String BOOK;

    @Value("${api.chapter}")
    private String CHAPTER;

    @Override
    public List<BibleReadingDto> getReading(BibleReadingRequest request) {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
            String url = BIBLE_READING +
                    BOOK + request.getBook() +
                    CHAPTER + request.getChapter();

            var response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<BibleReadingDto>>() {
                    }
            ).getBody();

            log.info("bible reading response {}", response);

            return response;

        }catch (Exception e){
            log.error("bible reading error: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to get bible reading");
        }
    }
}
