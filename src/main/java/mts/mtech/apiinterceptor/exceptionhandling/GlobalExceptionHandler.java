package mts.mtech.apiinterceptor.exceptionhandling;

import mts.mtech.apiinterceptor.dto.ErrorResponse;

import mts.mtech.apiinterceptor.utils.Response;
import mts.mtech.errorhandling.exception.InvalidRequestException;
import mts.mtech.errorhandling.exception.RecordNotFoundException;
import mts.mtech.errorhandling.exception.SystemErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Response<ErrorResponse>> handleRecordNotFoundException(RecordNotFoundException ex) {
        return exceptionMessageHandler(ex).toResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Response<ErrorResponse>> handleInvalidArgumentException(InvalidRequestException ex) {
        return exceptionMessageHandler(ex).toResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(SystemErrorException.class)
    public ResponseEntity<Response<ErrorResponse>> handleSystemErrorException(SystemErrorException ex) {
        return exceptionMessageHandler(ex).toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<ErrorResponse>> handleGeneralExceptions(Exception ex) {
        return exceptionMessageHandler(ex).toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response<ErrorResponse>> handleEnumConversionError(MethodArgumentTypeMismatchException ex) {
        return exceptionMessageHandler(ex).toResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private <T extends Throwable> Response<ErrorResponse> exceptionMessageHandler(T t) {
        String message = t.getMessage();

        if (t instanceof MethodArgumentTypeMismatchException ex &&
                ex.getRequiredType() != null &&
                ex.getRequiredType().isEnum()) {

            Class<?> enumClass = ex.getRequiredType();
            String invalidValue = Objects.requireNonNull(ex.getValue()).toString();
            String allowedValues = Arrays.toString(enumClass.getEnumConstants());

            message = String.format("Invalid value '%s'. Allowed values are: %s", invalidValue, allowedValues);
        }

        ErrorResponse errorResponse = new ErrorResponse(message);
        return new Response<ErrorResponse>().buildErrorResponse(errorResponse.getErrorMessage());
    }

}
