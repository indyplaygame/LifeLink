package dev.indy.lifelink.exception;

public class InvalidLoginCredentialsException extends RuntimeException {
    public InvalidLoginCredentialsException() {
        super("Invalid login credentials provided.");
    }
}
