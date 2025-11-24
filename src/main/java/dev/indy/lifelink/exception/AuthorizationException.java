package dev.indy.lifelink.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends HttpException{
    public AuthorizationException() {
        super(HttpStatus.UNAUTHORIZED, "This endpoint requires authentication.");
    }
}
