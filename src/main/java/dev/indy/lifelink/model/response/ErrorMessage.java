package dev.indy.lifelink.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorMessage(@JsonProperty("error") String message) {
}
