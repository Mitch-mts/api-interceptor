package mts.mtech.apiinterceptor.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;

/**
 * @author mitchellsevera
 * Created on 16/9/2025
 */
@Slf4j
public class LoggingUtility {

    public static void logWithContextAndLogLevel(Class<?> className, String methodName, String context, Object data, Level logLevel) {
        if (logLevel == null) {
            log.warn("Log level is null - {} - {} - {} - {}", className, methodName, context, data);
            return;
        }

        switch (logLevel) {
            case INFO -> log.info("<---{} --- {} --- {} --- {}--->", className, methodName, context, data);
            case DEBUG -> log.debug("<---{} --- {} --- {} --- {}--->", className, methodName, context, data);
            case WARN ->  log.warn("<---{} --- {} --- {} --- {}--->", className, methodName, context, data);
            case ERROR -> log.error("<---{} --- {} --- {} --- {}--->", className, methodName, context, data);
        }
    }
}
