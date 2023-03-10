package mts.mtech.apiinterceptor.dto.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Articles {
    @JsonProperty("status")
    private String status;

    @JsonProperty("totalResults")
    private Long totalResults;

    @JsonProperty("articles")
    private List<NewsDto> articles;
}
