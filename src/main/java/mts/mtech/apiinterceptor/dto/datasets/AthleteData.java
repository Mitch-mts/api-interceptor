package mts.mtech.apiinterceptor.dto.datasets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AthleteData {
    @JsonProperty("Discipline")
    private String discipline;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("NOC")
    private String country;
}

