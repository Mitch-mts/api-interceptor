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
public class Result implements Serializable {
    private String message;
    private boolean success;
    private String referenceId;
}
