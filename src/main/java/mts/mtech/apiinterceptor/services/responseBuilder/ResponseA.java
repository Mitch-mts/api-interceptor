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
@JsonTypeName("ResponseA")
public class ResponseA implements ServiceResponse{
    private String message;
    private String processFunction;

    @Override
    public String processFunction() {
        return "";
    }
}
