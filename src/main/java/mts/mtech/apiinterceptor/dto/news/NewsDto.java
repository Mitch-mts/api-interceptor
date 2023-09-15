package mts.mtech.apiinterceptor.dto.news;


import java.time.LocalDateTime;

public record NewsDto(
         Source source,
         String author,
         String title,
         String description,
         String url,
         String urlImage,
         LocalDateTime publishedAt,
         String content
) {

}
