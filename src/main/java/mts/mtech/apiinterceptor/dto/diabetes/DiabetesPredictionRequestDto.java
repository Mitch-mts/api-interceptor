package mts.mtech.apiinterceptor.dto.diabetes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiabetesPredictionRequestDto implements Serializable {
    private Integer age;
    private Gender gender;
    private Double bodyMassIndex;
    private Double bloodPressure;
    private Double glucoseLevel;
    private Double insulinLevel;
    private String skinThickness;
    private Integer numberOfPregnancies;
    private Double diabetesPedigreeFunction;
}
