package com.jonathansoriano.enterprisedevgroupproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// This class acts as a global exception handler for the entire application.
// It intercepts exceptions thrown from controllers/services and maps them to structured HTTP responses.

@Slf4j
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
        //Logging Exceptions
        log.warn("Search not found at {}: ", request.getRequestURI(), ex);
        // We retrieve said exception's message and return it in the ResponseEntity
        // along with HTTP status (404).

        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(wrapper, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionWrapper> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, HttpServletRequest request) {
        log.warn("Email already exists at {}: ", request.getRequestURI(), ex);

        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(wrapper, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });
        String errorMessage = errors.toString();

        log.warn("Property validation error(s) at {}: ", request.getRequestURI(), ex);

        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), "Property validation error(s):" + errorMessage, request.getRequestURI());

        return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
    }

    // Any other exception thrown will be caught here
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionWrapper> handleException(Exception ex, HttpServletRequest request) {
        String message = ex.getMessage();
        //Logging Exceptions
        log.error("Unhandled exception occurred at {}: ", request.getRequestURI(), ex);

        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong..." + message, request.getRequestURI());
        return new ResponseEntity<>(wrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
