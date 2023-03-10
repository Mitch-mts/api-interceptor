package mts.mtech.apiinterceptor.services.topheadlines;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NewsRequest {
    private String country;
    private String source;
    private String category;
}
