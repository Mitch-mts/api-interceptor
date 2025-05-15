package mts.mtech.apiinterceptor.services.responseBuilder;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonTypeName("ResponseB")
public class ResponseB implements ServiceResponse{
    private String description;
    private String processFunction;

    @Override
    public String processFunction() {
        return "";
    }
}
