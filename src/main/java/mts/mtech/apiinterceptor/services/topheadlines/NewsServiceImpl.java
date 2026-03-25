package mts.mtech.apiinterceptor.services.topheadlines;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.news.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NewsServiceImpl implements NewsService{
    @Autowired
    RestTemplate restTemplate;

    @Value("${api-key.news}")
    private String NEWS_API_KEY;

    @Value("${api.news-top-headlines}")
    private String TOP_HEADLINES;

    @Value("${api.news-base-url}")
    private String NEWS_BASE_URL;

    @Override
    public Articles getAllNews(NewsRequest newsRequest) {
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("X-Api-Key", NEWS_API_KEY);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);

            log.info("url: {}", NEWS_BASE_URL + TOP_HEADLINES + "?country=" + newsRequest.getCountry());
            log.info("body: {}", httpEntity);

            var response = restTemplate.exchange(
                                            NEWS_BASE_URL + TOP_HEADLINES + "?country=" + newsRequest.getCountry(),
                                            HttpMethod.GET,
                                            httpEntity,
                                            new ParameterizedTypeReference<Articles>() {
                                            }
                                    ).getBody();
            log.info("response: {}", response.getStatus());
            log.info("response: {}", response.getTotalResults());
            log.info("response: {}", response.getArticles());
            log.info("response: {}", response);

            return response;

        }catch (Exception e){
            log.error("error: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to get News");
        }
    }


}
