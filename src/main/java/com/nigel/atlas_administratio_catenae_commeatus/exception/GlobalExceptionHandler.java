package com.nigel.atlas_administratio_catenae_commeatus.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return buildError(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicate(
            DuplicateResourceException ex,
            HttpServletRequest request) {

        return buildError(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField()
                        + ": "
                        + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildError(
                HttpStatus.BAD_REQUEST,
                message,
                request.getRequestURI());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        return buildError(
                HttpStatus.CONFLICT,
                "Database constraint violation",
                request.getRequestURI());
    }

    private ResponseEntity<ApiError> buildError(
            HttpStatus status,
            String message,
            String path) {

        ApiError error = new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path);

        return ResponseEntity
                .status(status)
                .body(error);
    }
}
