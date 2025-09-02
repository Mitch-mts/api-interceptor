package mts.mtech.apiinterceptor.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
@Slf4j
public class ControllerLogging { @Around("within(mts.mtech.apiinterceptor.controller..*)")
public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
    String className = joinPoint.getSignature().getDeclaringTypeName();
    String methodName = joinPoint.getSignature().getName();
    Object[] methodArgs = joinPoint.getArgs();

    String requestBody = extractRequestBody(joinPoint);
    String requestUrl = extractRequestUrl();
    logWithContext(className, methodName, "Request URL", requestUrl);
    logWithContext(className, methodName, "Request Body", requestBody);

    Object result;

    try{
        result = joinPoint.proceed();
    } catch (Throwable e){
        logWithContext(className, methodName, "Error", e.getMessage());
        throw e;
    }

    Object responseBody = (result instanceof ResponseEntity)
            ? (((ResponseEntity<?>) result).getBody())
            : result;

    String response = serialiseSafe(responseBody);
    logWithContext(className, methodName, "Response", response);

    return result;
}

    private String serialiseSafe(Object responseBody) {
        try{
            return new ObjectMapper().writeValueAsString(responseBody);

        } catch (JsonProcessingException ex) {
            return String.valueOf(responseBody);
        }
    }

    private void logWithContext(String className, String methodName, String requestUrl, String requestUrl1) {
        log.info("<---- {} ----- {} ----- {} ----- {} ---->", className, methodName, requestUrl, requestUrl1);
    }

    private String extractRequestUrl() {
        HttpServletRequest request = getCurrentHttpRequest();

        if (request == null) {
            return "unknown request URL";
        }

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();

        return query == null
                ? String.format("%s %s", method, uri)
                : String.format("%s %s?%s", method, uri, query);

    }

    private HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (requestAttributes != null) ? requestAttributes.getRequest() : null;
    }

    private String extractRequestBody(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();

        for(int i = 0; i < parameters.length; i++) {
            if(parameters[i].isAnnotationPresent(RequestBody.class)) {
                return serialiseSafe(args[i]);
            }
        }
        return "[no request body]";
    }
}
