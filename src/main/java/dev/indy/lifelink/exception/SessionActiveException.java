package dev.indy.lifelink.exception;

public class SessionActiveException extends Exception {
    public SessionActiveException() {
        super("An user is already logged in this session.");
    }
}
