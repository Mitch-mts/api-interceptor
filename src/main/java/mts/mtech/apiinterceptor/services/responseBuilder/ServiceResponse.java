package mts.mtech.apiinterceptor.services.responseBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(
        value = "processFunction",
        allowSetters = true
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "processFunction", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResponseA.class, name = "ResponseA"),
        @JsonSubTypes.Type(value = ResponseB.class, name = "ResponseB")

})
public interface ServiceResponse {
    String processFunction();
}
