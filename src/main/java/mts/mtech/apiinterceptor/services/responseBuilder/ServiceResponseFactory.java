package mts.mtech.apiinterceptor.services.responseBuilder;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceResponseFactory {
    private final List<ResponseBuilderStrategy> responseBuilderStrategies;

    public ServiceResponseFactory(List<ResponseBuilderStrategy> responseBuilderStrategies) {
        this.responseBuilderStrategies = responseBuilderStrategies;
    }


    public ServiceResponse buildServiceResponse(String processFunction) {
        return responseBuilderStrategies.stream()
                .filter(s -> s.supportsStrategy(processFunction))
                .findFirst()
                .orElseThrow()
                .build("Test");
    }
}
