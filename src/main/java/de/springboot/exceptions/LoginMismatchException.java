package de.springboot.exceptions;

public class LoginMismatchException extends RuntimeException {

    public LoginMismatchException(String message) {
        super(message);
    }

    public LoginMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
