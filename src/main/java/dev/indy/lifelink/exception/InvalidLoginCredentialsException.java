package dev.indy.lifelink.exception;

public class InvalidLoginCredentialsException extends Exception {
    public InvalidLoginCredentialsException() {
        super("Invalid login credentials provided.");
    }
}
