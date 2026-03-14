package com.jonathansoriano.enterprisedevgroupproject.exception;

/**
 * Custom exception thrown when a search query does not return any results.
 * Extends RuntimeException for unchecked exception handling.
 *
 * @author [Author Name]
 * @version [Version]
 * @since [Release Version]
 */
public class SearchNotFoundException extends RuntimeException {
    public SearchNotFoundException(String message) {
        super(message);
    }
}
