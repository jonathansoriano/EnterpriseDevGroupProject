package com.jonathansoriano.enterprisedevgroupproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionTranslator {

    /**
     * Handles custom SearchNotFoundException.
     * Returns a 404 Not Found response.
     */
    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<ExceptionWrapper> handleSearchNotFoundException(SearchNotFoundException ex, HttpServletRequest request) {
        log.warn("Search not found at {}: {}", request.getRequestURI(), ex.getMessage());
        
        ExceptionWrapper wrapper = new ExceptionWrapper(
                HttpStatus.NOT_FOUND.value(), 
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(wrapper, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles database constraint violations (e.g., duplicate email during sign-up).
     * Returns a 409 Conflict response.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionWrapper> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.error("Data integrity violation at {}: ", request.getRequestURI(), ex);
        
        ExceptionWrapper wrapper = new ExceptionWrapper(
                HttpStatus.CONFLICT.value(),
                "A data conflict occurred. A record with these details (such as email) may already exist.",
                request.getRequestURI()
        );

        return new ResponseEntity<>(wrapper, HttpStatus.CONFLICT);
    }

    /**
     * Handles validation errors triggered by @Valid annotations on DTOs.
     * Returns a 400 Bad Request response with concatenated field errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.warn("Validation failed at {}: {}", request.getRequestURI(), ex.getMessage());
        
        // Extract all field errors and join them into a readable string
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ExceptionWrapper wrapper = new ExceptionWrapper(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed - " + errorMessage,
                request.getRequestURI()
        );

        return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
    }

    /**
     * Fallback handler for any uncaught exceptions.
     * Returns a 500 Internal Server Error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionWrapper> handleException(Exception ex, HttpServletRequest request) {
        // Log the full stack trace for unexpected errors
        log.error("Unhandled exception occurred at {}: ", request.getRequestURI(), ex);
        
        ExceptionWrapper wrapper = new ExceptionWrapper(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred. Please try again later.", 
                request.getRequestURI()
        );
        
        return new ResponseEntity<>(wrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}