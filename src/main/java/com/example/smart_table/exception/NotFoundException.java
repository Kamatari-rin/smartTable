package com.example.smart_table.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message,Object... arg) {
        super(MessageFormat.format(message, arg));
    }

    public static Supplier<NotFoundException> NOT_FOUND_EXCEPTION(String message) {
        return () -> new NotFoundException(message);
    }
}
