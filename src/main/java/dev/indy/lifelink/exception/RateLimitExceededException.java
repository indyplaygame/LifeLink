package dev.indy.lifelink.exception;

import org.springframework.http.HttpStatus;

public class RateLimitExceededException extends HttpException {
    public RateLimitExceededException() {
        super(HttpStatus.TOO_MANY_REQUESTS, "Rate limit exceeded");
    }
}
