package com.example.smart_table.error.service;

import com.example.smart_table.error.model.ApiError;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorUtil {

    public static ApiError createApiError(String message, HttpStatus status, String errorCode, String path, List<String> details) {
        return ApiError.builder()
                .message(message)
                .status(status.name())
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .path(path)
                .details(details)
                .build();
    }

    public static ApiError createApiError(String message, HttpStatus status, String errorCode, String path) {
        return createApiError(message, status, errorCode, path, null);
    }
}
