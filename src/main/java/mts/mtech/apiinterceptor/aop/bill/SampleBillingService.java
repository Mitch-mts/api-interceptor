package mts.mtech.apiinterceptor.aop.bill;

import mts.mtech.apiinterceptor.aop.MyAspect;
import org.springframework.stereotype.Service;

@Service
public class SampleBillingService {
    @MyAspect
    public void createBill(){
        System.out.println("Bill created");
    }

    @MyAspect
    public void createBill(Long price){
        System.out.println("bill created for  " + price);
    }
}
