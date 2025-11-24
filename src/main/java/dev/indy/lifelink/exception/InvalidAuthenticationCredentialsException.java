package dev.indy.lifelink.exception;

public class InvalidAuthenticationCredentialsException extends Exception {
    public InvalidAuthenticationCredentialsException() {
        super("Invalid authentication credentials provided.");
    }
}
