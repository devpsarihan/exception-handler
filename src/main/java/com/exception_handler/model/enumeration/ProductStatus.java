package com.exception_handler.model.enumeration;

import com.exception_handler.configuration.advice.ErrorCode;
import com.exception_handler.configuration.advice.exception.ProductApiException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ProductStatus {

    ACTIVE(1),
    PASSIVE(2),
    ON_SALE(3);

    private final int code;

    ProductStatus(int code) {
        this.code = code;
    }

    public static ProductStatus from(int code) {
        return Arrays.stream(values())
            .filter(productStatus -> productStatus.code == code)
            .findFirst()
            .orElseThrow(() -> new ProductApiException(ErrorCode.PRODUCT_STATUS_NOT_FOUND, code));
    }
}
