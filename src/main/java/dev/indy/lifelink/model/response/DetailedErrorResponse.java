package dev.indy.lifelink.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record DetailedErrorResponse(
    String timestamp,
    int status,
    String error,
    String path,
    @JsonProperty("details") Object body
) {
    public DetailedErrorResponse(String timestamp, int status, String error, String path, Object body) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.body = body;
    }

    public DetailedErrorResponse(int status, String error, String path, Object body) {
        this(Instant.now().toString(), status, error, path, body);
    }
}
