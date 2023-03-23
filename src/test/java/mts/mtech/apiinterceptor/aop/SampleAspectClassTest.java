package mts.mtech.apiinterceptor.aop;

import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SampleAspectClassTest {

    @Autowired
    SampleAspectClass sampleAspectClass;

    @Test
    void shipStuff() {
        sampleAspectClass.shipStuff();
        sampleAspectClass.shipStuffToBill();
    }
}