package dev.indy.lifelink.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(@JsonProperty("error") String message) {}
