package mts.mtech.apiinterceptor.aop.bill;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleBillComponent {

    /*
     * within() pointcut is used to match methods in a given package or sub-package
     * within(mts.mtech.apiinterceptor.aop.bill.*) is used to match all methods in the specified package
     * */
    @Pointcut("within(mts.mtech.apiinterceptor.aop.bill.SampleBillingService)")
    public void logPointcutWithin(){}

    @Pointcut("execution(public void mts.mtech.apiinterceptor.aop.bill.SampleBillingService.createBill(Long))")
    public void logMethodWithArgs(){}

    @Before("logPointcutWithin()")
    public void logMethodWithin(){
        System.out.println("In Aspect from within");
    }

    @Before("logMethodWithArgs()")
    public void logMethodWithArguments(){
        System.out.println("In Aspect with args");
    }


}
