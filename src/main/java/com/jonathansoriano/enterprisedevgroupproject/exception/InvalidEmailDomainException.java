package com.jonathansoriano.enterprisedevgroupproject.exception;

/*
 * Custom exception thrown when a user attempts to sign up with an email that does not have a .edu domain.
 * Ensures that only educational institution email addresses are allowed for student registration.
 *
 * @author Chris Vu - VaiVuong
 * @version 1.0
 * @since 3/14/26
 */
public class InvalidEmailDomainException extends RuntimeException {
    public InvalidEmailDomainException(String message) {
        super(message);
    }
}
