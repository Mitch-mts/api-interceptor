package mts.mtech.apiinterceptor.dto.quotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDto {
    @JsonProperty(value = "q")
    private String quote;
    @JsonProperty(value = "a")
    private String code;
    @JsonProperty(value = "h")
    private String body;
}
