package com.exception_handler.configuration.advice.exception;

import com.exception_handler.configuration.advice.ErrorCode;
import lombok.Data;

@Data
public class ProductApiException extends RuntimeException {

    private final int code;
    private final Integer httpStatusCode;
    private final Object[] args;

    public ProductApiException(ErrorCode errorCode, Object... args) {
        this.code = errorCode.getCode();
        this.httpStatusCode = errorCode.getHttpStatusCode();
        this.args = args;
    }
}
