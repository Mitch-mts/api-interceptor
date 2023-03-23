package mts.mtech.apiinterceptor.aop.gameofthrones;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import mts.mtech.apiinterceptor.dto.gameofthrones.Continents;
import java.util.List;

@Component
@Aspect
@Slf4j
public class GameOfThronesAspect {

    @Pointcut("execution(* mts.mtech.apiinterceptor.services.gameofthrones.ContinentsServiceImpl.getContinents())")
    public void logContinents(){}

    @AfterReturning("logContinents()")
    public void logGameOfThronesContinents(){
        log.info("game of thrones aspects from aspect ");
        System.out.println("game of thrones continents from aspect");
    }
}
