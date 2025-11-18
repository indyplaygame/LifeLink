package dev.indy.lifelink.model.request;

import dev.indy.lifelink.validation.constraints.ValidPesel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(
    @ValidPesel
    String pesel,

    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*\\-_]+$", message = "Password can only contain alphanumeric characters and special characters (!@#$%^&*-_)")
    @Length(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    String password
) {}
