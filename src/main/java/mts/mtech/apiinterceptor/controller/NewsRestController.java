package mts.mtech.apiinterceptor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.news.Articles;
import mts.mtech.apiinterceptor.dto.news.Response;
import mts.mtech.apiinterceptor.dto.news.Sources;
import mts.mtech.apiinterceptor.services.sources.NewsSourcesService;
import mts.mtech.apiinterceptor.services.topheadlines.NewsRequest;
import mts.mtech.apiinterceptor.services.topheadlines.NewsService;
import mts.mtech.apiinterceptor.utils.Constants;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/news")
@Tag(name = "News APIs", description = "News Api from around the world")
@Slf4j
public class NewsRestController {
    private final NewsService newsService;
    private final NewsSourcesService newsSourcesService;

    public NewsRestController(NewsService newsService, NewsSourcesService newsSourcesService) {
        this.newsService = newsService;
        this.newsSourcesService = newsSourcesService;
    }

    @Operation(summary = "get today's top headlines from around the world, current affairs as they happen", description = "Api for top-headlines")
    @PostMapping("/top-headlines")
    public Response<Articles> getTopHeadlines(@RequestBody NewsRequest newsRequest){
        return new Response<Articles>().buildSuccessResponse(Constants.SUCCESS, newsService.getAllNews(newsRequest));
    }

    @Operation(summary = "get news sources", description = "Api for news sources")
    @GetMapping("/sources")
    public Response<List<Sources>> getSources(){
        return new Response<List<Sources>>().buildSuccessResponse(Constants.SUCCESS, newsSourcesService.getSources());
    }

}
