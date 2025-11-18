package dev.indy.lifelink.exception;

public class PatientExistsException extends RuntimeException {
    public PatientExistsException() {
        super("Patient with the given PESEL already exists.");
    }
}
