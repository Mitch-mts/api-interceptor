package mts.mtech.apiinterceptor.aop.bill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleBillingServiceTest {

    @Autowired
    SampleBillingService service;

    @Test
    void testWithin() {
        service.createBill();
    }

    @Test
    void testWithArgs() {
        service.createBill(20L);
    }
}