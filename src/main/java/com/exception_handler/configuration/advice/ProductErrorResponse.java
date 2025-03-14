package com.exception_handler.configuration.advice;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductErrorResponse implements Serializable {

    private int code;
    private String message;
    private Long timestamp;
}
