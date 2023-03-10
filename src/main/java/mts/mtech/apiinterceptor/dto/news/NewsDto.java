package mts.mtech.apiinterceptor.dto.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NewsDto {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlImage;
    private LocalDateTime publishedAt;
    private String content;
}
