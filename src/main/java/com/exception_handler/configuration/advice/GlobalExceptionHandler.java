package com.exception_handler.configuration.advice;

import com.exception_handler.configuration.advice.exception.ProductApiException;
import com.exception_handler.configuration.internationalization.util.ResourceBundleMessageUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ResourceBundleMessageUtil resourceBundleMessageUtil;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProductApiException.class)
    public ResponseEntity<ProductErrorResponse> redisApiExceptionHandler(ProductApiException e) {
        logger.info("Api related exception => {}", e.getMessage());
        ProductErrorResponse errorResponse = ProductErrorResponse.builder()
            .code(e.getCode())
            .message(resourceBundleMessageUtil.getMessage(e.getCode(), e.getArgs()))
            .timestamp(System.currentTimeMillis())
            .build();
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(e.getHttpStatusCode()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ProductErrorResponse> runtimeExceptionHandler(RuntimeException e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        ProductErrorResponse errorResponse = ProductErrorResponse.builder()
            .code(100)
            .message(resourceBundleMessageUtil.getMessage(100))
            .timestamp(System.currentTimeMillis())
            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

