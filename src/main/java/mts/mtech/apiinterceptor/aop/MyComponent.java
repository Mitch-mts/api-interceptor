package mts.mtech.apiinterceptor.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyComponent {

    /*
    * this is a pointcut for specifying methods with a given annotation
    * */
    @Pointcut("@annotation(MyAspect)")
    public void logPointCut(){}


    /*
    * if you use execution(public void mts.mtech.apiinterceptor.aop.SampleAspectClass.shipStuffToBill()) it will look for the joinPoint with the specified name
    * if you use execution(public void mts.mtech.apiinterceptor.aop.SampleAspectClass.*()) it will match any public void method that doesn't take parameters in SampleAspectClass
    * if you use execution(public void mts.mtech.apiinterceptor.aop.SampleAspectClass.*(..)) it will match any public void method that takes zero or more parameters in SampleAspectClass
    * */
    @Pointcut("execution(public void mts.mtech.apiinterceptor.aop.SampleAspectClass.shipStuffToBill())")
    public void logPointCutWithExecution(){}

    @Before("logPointCut()")
    public void logAllMethodCallsAdvice(){
        System.out.println("In Aspect");
    }

    /*
    * @AfterReturning it used after the normal execution of a method
    * @AfterThrowing is used after an exception is thrown while method execution
    * */
    @After("logPointCut()")
    public void logMethodAfter(){
        System.out.println("in After Aspect");
    }

    @Before("logPointCutWithExecution()")
    public void logMethodWithExecution(){
        System.out.println("In service with execution");
    }


}
