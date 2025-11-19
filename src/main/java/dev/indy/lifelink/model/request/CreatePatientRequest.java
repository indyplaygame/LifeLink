package dev.indy.lifelink.model.request;

import dev.indy.lifelink.validation.ValidationGroups;
import dev.indy.lifelink.validation.constraints.ValidPesel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CreatePatientRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Date of birth cannot be empty")
    @Pattern(groups = ValidationGroups.OnCreate.class, regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Date of birth must be in the format DD/MM/YYYY")
    String dateOfBirth,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Length(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    String email,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "PESEL cannot be empty")
    @ValidPesel
    String pesel,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Password cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*\\-_]+$", message = "Password can only contain alphanumeric characters and special characters (!@#$%^&*-_)")
    @Length(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    String password,

    @Valid
    CreatePersonRequest person,

    @Valid
    CreatePersonRequest emergencyContact
) {}
