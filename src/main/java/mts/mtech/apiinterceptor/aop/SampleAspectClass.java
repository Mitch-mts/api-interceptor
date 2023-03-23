package mts.mtech.apiinterceptor.aop;

import org.springframework.stereotype.Service;

@Service
public class SampleAspectClass {

    @MyAspect
    public void shipStuff(){
        System.out.println("In Service");
    }

    @MyAspect
    public void shipStuffToBill(){
        System.out.println("In service with Bill");
    }
}
