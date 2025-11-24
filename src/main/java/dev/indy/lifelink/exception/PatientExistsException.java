package dev.indy.lifelink.exception;

public class PatientExistsException extends Exception {
    public PatientExistsException() {
        super("Patient with the given PESEL already exists.");
    }
}
