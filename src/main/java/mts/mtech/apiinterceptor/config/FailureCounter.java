package mts.mtech.apiinterceptor.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

/**
 * @author mitchellsevera
 * Created on 29/9/2025
 */
@Component
public class FailureCounter {
    private final Counter charactersfFailureCounter;

    public FailureCounter(MeterRegistry meterRegistry) {
        this.charactersfFailureCounter = Counter.builder("api.characters.failure.count")
                                                .description("Counts the number of failed attempts to fetch character details")
                                                .tag("api", "characters")
                                                .register(meterRegistry);
    }

    public void recordFailure() {
        charactersfFailureCounter.increment();
    }
}
