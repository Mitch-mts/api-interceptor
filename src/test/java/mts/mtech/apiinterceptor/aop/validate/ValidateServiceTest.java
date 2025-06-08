package mts.mtech.apiinterceptor.aop.validate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ValidateServiceTest {
    private ValidateService service;

    @BeforeEach
    void setUp() {
        service = new ValidateService();
    }

    @Test
    void validateNumber() {
        service.validateNumber(10);
    }
}