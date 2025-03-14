package com.exception_handler.configuration.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNKNOWN_ERROR(100, 500),
    CATEGORY_NOT_FOUND(101, 412),
    PRODUCT_STATUS_NOT_FOUND(102, 412),
    PRODUCT_NOT_FOUND_ERROR(103, 412),
    PRODUCT_ALREADY_CREATED(104, 412),
    PRODUCT_CANNOT_UPDATE(105, 412);

    private final int code;
    private final Integer httpStatusCode;
}
