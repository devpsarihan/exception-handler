package com.exception_handler.configuration.internationalization.util;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResourceBundleMessageUtil {

    private final MessageSource messageSource;

    public String getMessage(final int code) {
        return messageSource.getMessage(String.valueOf(code), null, Locale.getDefault());
    }

    public String getMessage(final int code, final Object... args) {
        return messageSource.getMessage(String.valueOf(code), args, Locale.getDefault());
    }
}
