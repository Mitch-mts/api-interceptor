package mts.mtech.apiinterceptor.aop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SampleAspectClassTest {
    private SampleAspectClass sampleAspectClass;

    @BeforeEach
    void setUp() {
        sampleAspectClass = new SampleAspectClass();
    }

    @Test
    void shipStuff() {
        sampleAspectClass.shipStuff();
        sampleAspectClass.shipStuffToBill();
    }
}