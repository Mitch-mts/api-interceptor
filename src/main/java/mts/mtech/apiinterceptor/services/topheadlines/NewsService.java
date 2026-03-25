package mts.mtech.apiinterceptor.services.topheadlines;

import mts.mtech.apiinterceptor.dto.news.Articles;

public interface NewsService {
    Articles getAllNews(NewsRequest newsRequest);
}
