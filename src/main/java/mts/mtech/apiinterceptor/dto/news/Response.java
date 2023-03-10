package mts.mtech.apiinterceptor.dto.news;

import lombok.*;
import mts.mtech.apiinterceptor.utils.Constants;

import java.io.Serializable;

/**
 * @author Mitchell Tawanda Severa
 * 10/17/18
 * 11:26 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class Response<T> implements Serializable {
    private int statusCode;
    private boolean success;
    private String message;
    private T result;

    public  Response<T> buildSuccessResponse(String message) {
        this.statusCode = Constants.SUCCESS_INT_VALUE;
        this.success = true;
        this.message = message;
        this.result = null;
        return this;
    }

    public Response<T> buildSuccessResponse(String message, final T result) {
        this.statusCode = Constants.SUCCESS_INT_VALUE;
        this.success = true;
        this.message = message;
        this.result = result;
        return this;
    }

    public Response<T> buildErrorResponse(String message) {
        this.statusCode = Constants.FAILURE_INT_VALUE;
        this.success = false;
        this.message = message;
        this.result = null;
        return this;
    }

    public Response<T> buildErrorResponse(String message, final T result) {
        this.statusCode = Constants.FAILURE_INT_VALUE;
        this.success = false;
        this.message = message;
        this.result = result;
        return this;
    }
}