package com.jonathansoriano.enterprisedevgroupproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionTranslator {
    //PURPOSE OF THIS CLASS: It catches exceptions thrown in your app’s controller/service layer and turns them into HTTP responses.


    //Any time this "SearchNotFoundException" is thrown, this class will redirect here
    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<ExceptionWrapper> handleSearchNotFoundException(SearchNotFoundException ex, HttpServletRequest request)
    {
        //We retrieve said exception's message and return it in the ResponseEntity along with HTTP status (404).

        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(wrapper, HttpStatus.NOT_FOUND);
    }
    //Any other exception thrown will be caught here
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionWrapper> handleException(Exception ex, HttpServletRequest request)
    {
        String message = ex.getMessage();
        ExceptionWrapper wrapper = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong..." + message, request.getRequestURI());
        return new ResponseEntity<>(wrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
