package com.exception_handler.model.enumeration;

import com.exception_handler.configuration.advice.ErrorCode;
import com.exception_handler.configuration.advice.exception.ProductApiException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Category {

    TECHNOLOGY(1),
    COSMETIC(2),
    FOOD(3),
    ELECTRONICS(4),
    FASHION(5),
    FURNITURE(6),
    GAMING(7);

    private final int code;

    Category(int code) {
        this.code = code;
    }

    public static Category from(int code) {
        return Arrays.stream(values())
            .filter(category -> category.code == code)
            .findFirst()
            .orElseThrow(() -> new ProductApiException(ErrorCode.CATEGORY_NOT_FOUND, code));
    }
}
