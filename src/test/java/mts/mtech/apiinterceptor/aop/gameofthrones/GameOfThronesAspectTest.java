package mts.mtech.apiinterceptor.aop.gameofthrones;

import mts.mtech.apiinterceptor.services.gameofthrones.ContinentsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameOfThronesAspectTest {

    @Autowired
    ContinentsServiceImpl service;

    @Test
    void logGameOfThronesContinents(){
        service.getContinents();
    }
}