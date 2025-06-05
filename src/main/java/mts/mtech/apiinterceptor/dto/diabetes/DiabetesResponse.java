package mts.mtech.apiinterceptor.dto.diabetes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiabetesResponse implements Serializable {
    private String firstName;
    private String lastName;
    private boolean predictionResult;
    private LocalDateTime dateCreated;
}
