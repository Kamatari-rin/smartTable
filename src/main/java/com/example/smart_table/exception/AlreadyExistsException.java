package com.example.smart_table.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message, Object ... arg) {
        super(MessageFormat.format(message, arg));
    }

    public static Supplier<AlreadyExistsException> ALREADY_EXIST_EXCEPTION(String message, Object... args) {
        return () -> new AlreadyExistsException(message, args);
    }
}
