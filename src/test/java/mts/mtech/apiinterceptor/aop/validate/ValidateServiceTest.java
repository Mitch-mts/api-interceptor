package mts.mtech.apiinterceptor.aop.validate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidateServiceTest {

    @Autowired
    ValidateService service;

    @Test
    void validateNumber() {
        service.validateNumber(10);
    }
}