package mts.mtech.apiinterceptor.aop.validate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateAspect {

    @Pointcut("within(mts.mtech.apiinterceptor.aop.validate.ValidateService)")
    public void logMethod(){}

    @Around("logMethod()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("In Advice Around Aspect");

        int arg = (int) joinPoint.getArgs()[0];
        if(arg < 0){
            throw new RuntimeException("Argument should not be negative");
        }else{
            joinPoint.proceed();
        }
    }
}
