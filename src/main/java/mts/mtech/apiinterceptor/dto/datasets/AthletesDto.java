package mts.mtech.apiinterceptor.dto.datasets;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AthletesDto {
    private String count;
    private String message;
    private List<AthleteData> data;
}
