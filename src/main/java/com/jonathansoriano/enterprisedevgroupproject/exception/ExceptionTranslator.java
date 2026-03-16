package com.jonathansoriano.enterprisedevgroupproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.stream.Collectors;

// This class acts as a global exception handler for the entire application.
// It intercepts exceptions thrown from controllers/services and maps them to structured HTTP responses.
// IMPROVEMENT: Add a Logger (e.g., @Slf4j from Lombok) and log each caught exception.
// Without logging, exceptions are silently converted to HTTP responses and the root cause
// is never recorded in application logs, making debugging very difficult.
@ControllerAdvice()
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionTranslator {
    // PURPOSE OF THIS CLASS: It catches exceptions thrown in your app’s
    // controller/service layer and turns them into HTTP responses.

    // Any time this "SearchNotFoundException" is thrown, this class will redirect
    // here
    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<ExceptionWrapper> handleSearchNotFoundException(SearchNotFoundException ex,
            HttpServletRequest request) {
        // We retrieve said exception's message and return it in the ResponseEntity
        // along with HTTP status (404).

        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(wrapper, HttpStatus.NOT_FOUND);
    }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExceptionWrapper> handleValidationException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        // CHANGE NOTE (Rohit Vijai, 2026-03-15): Added handler for MethodArgumentNotValidException (triggered by @Valid) — collects per-field error messages and returns HTTP 400 Bad Request instead of falling through to the generic 500 handler.
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining("; "));

        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), message,
            request.getRequestURI());
        return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ExceptionWrapper> handleUnreadableMessage(HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        // CHANGE NOTE (Rohit Vijai, 2026-03-15): Added handler for HttpMessageNotReadableException — returns HTTP 400 Bad Request when the request body is missing or contains malformed JSON, instead of falling through to the generic 500 handler.
        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(),
            "Request body is missing or malformed", request.getRequestURI());
        return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ExceptionWrapper> handleDataIntegrityViolation(DataIntegrityViolationException ex,
            HttpServletRequest request) {
        // CHANGE NOTE (Rohit Vijai, 2026-03-15): Added handler for DataIntegrityViolationException (e.g., duplicate email) — returns HTTP 409 Conflict with a safe message instead of a generic 500.
        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.CONFLICT.value(),
            "Data conflict detected. Please verify unique and required fields.", request.getRequestURI());
        return new ResponseEntity<>(wrapper, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(IllegalStateException.class)
        public ResponseEntity<ExceptionWrapper> handleIllegalStateException(IllegalStateException ex,
            HttpServletRequest request) {
        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
            request.getRequestURI());
        return new ResponseEntity<>(wrapper, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    // Any other exception thrown will be caught here
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionWrapper> handleException(Exception ex, HttpServletRequest request) {
        // CHANGE NOTE (Rohit Vijai, 2026-03-15): Removed ex.getMessage() from the generic 500 response body to avoid leaking internal stack/exception details to API callers.
        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Something went wrong", request.getRequestURI());
        return new ResponseEntity<>(wrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // IMPROVEMENT: Add a dedicated handler for DataIntegrityViolationException to
    // return
    // 409 Conflict with a user-friendly message like "A student with this email
    // already exists."
    // Currently, duplicate-email inserts fall through to the generic Exception
    // handler above
    // and return a vague 500 Internal Server Error.

    // IMPROVEMENT: Add a handler for MethodArgumentNotValidException (triggered by
    // @Valid)
    // to return 400 Bad Request with field-level validation error details.
}
