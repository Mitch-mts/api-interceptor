package mts.mtech.apiinterceptor.services.quotes;

import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.quotes.QuoteDto;
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
public class QuotesServiceImpl implements QuotesService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${quotes.random}")
    private String QUOTES;

    @Value("${quotes.base-url}")
    private String BASEURL;

    @Override
    public QuoteDto getQuote() {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
            String url = BASEURL + QUOTES;

            log.info("quotes url: {}", url);

            var response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<QuoteDto>>() {
                    }
            ).getBody();

            log.info("quotes response {}", response);

            if (response == null || response.isEmpty()) {
                log.warn("Received empty or null quote list.");
                throw new IllegalStateException("No quotes found in response");
            }

            return response.get(0);

        }catch (Exception e){
            log.error("quotes error: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to get quotes");
        }
    }

}
