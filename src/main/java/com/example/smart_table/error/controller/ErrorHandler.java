package com.example.smart_table.error.controller;

import com.example.smart_table.error.model.ApiError;
import com.example.smart_table.error.service.ErrorUtil;
import com.example.smart_table.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ApiError apiError = ErrorUtil.createApiError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                "NOT_FOUND",
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleValidationExceptions(Exception ex, WebRequest request) {
        List<String> errorDetails = ex instanceof MethodArgumentNotValidException

                ? ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList())

                : ((ConstraintViolationException) ex).getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());


        ApiError apiError = ErrorUtil.createApiError(
                "Validation error",
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                request.getDescription(false),
                errorDetails
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        ApiError apiError = ErrorUtil.createApiError(
                "Data integrity violation",
                HttpStatus.CONFLICT,
                "DATA_INTEGRITY_VIOLATION",
                request.getDescription(false),
                List.of(ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> handleAllExceptions(Throwable ex, WebRequest request) {
        ApiError apiError = ErrorUtil.createApiError(
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }
}
