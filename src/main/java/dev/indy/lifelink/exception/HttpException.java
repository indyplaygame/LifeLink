package dev.indy.lifelink.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {
    private final HttpStatus _status;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this._status = status;
    }

    public HttpStatus getStatus() {
        return this._status;
    }
}
