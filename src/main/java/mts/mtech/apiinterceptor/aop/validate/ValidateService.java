package mts.mtech.apiinterceptor.aop.validate;

import org.springframework.stereotype.Service;

@Service
public class ValidateService {

    public void validateNumber(int number){
        System.out.println(number + " is valid");
    }
}
