package mts.mtech.apiinterceptor.services.responseBuilder;

public interface ResponseBuilderStrategy {
    boolean supportsStrategy(String processFunction);
    ServiceResponse build(String serviceResponse);
}
