package com.jonathansoriano.enterprisedevgroupproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// This class acts as a global exception handler for the entire application.
// It intercepts exceptions thrown from controllers/services and maps them to structured HTTP responses.
// IMPROVEMENT: Add a Logger (e.g., @Slf4j from Lombok) and log each caught exception.
// Without logging, exceptions are silently converted to HTTP responses and the root cause
// is never recorded in application logs, making debugging very difficult.
/**
 * Global exception handler that translates application exceptions into structured HTTP responses.
 * Catches exceptions from controllers and services and maps them to appropriate error responses.
 *
 * @author [Author Name]
 * @version [Version]
 * @since [Release Version]
 */
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

    // Any other exception thrown will be caught here
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionWrapper> handleException(Exception ex, HttpServletRequest request) {
        String message = ex.getMessage();
        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong..." + message, request.getRequestURI());
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
