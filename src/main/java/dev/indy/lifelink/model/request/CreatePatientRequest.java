package dev.indy.lifelink.model.request;

import dev.indy.lifelink.model.Patient;
import dev.indy.lifelink.validation.constraints.ValidDate;
import dev.indy.lifelink.validation.constraints.ValidPesel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record CreatePatientRequest(
    @NotBlank(groups = OnCreate.class, message = "Date of birth cannot be empty")
    @ValidDate(groups = {OnCreate.class, OnUpdate.class}, message = "Date of birth must be in the format DD-MM-YYYY")
    String dateOfBirth,

    @NotBlank(groups = OnCreate.class, message = "Email cannot be empty")
    @Email(groups = {OnCreate.class, OnUpdate.class}, message = "Invalid email format")
    @Length(groups = {OnCreate.class, OnUpdate.class}, min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    String email,

    @NotBlank(groups = OnCreate.class, message = "PESEL cannot be empty")
    @ValidPesel(groups = {OnCreate.class, OnUpdate.class})
    String pesel,

    @NotBlank(groups = OnCreate.class, message = "Password cannot be empty")
    @Length(groups = OnCreate.class, min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(
        groups = OnCreate.class,
        regexp = "(?i)^[a-z0-9!@#$%^&*\\-_]+$",
        message = "Password can only contain alphanumeric characters and special characters (!@#$%^&*-_)"
    )
    String password,

    @NotNull(groups = OnCreate.class, message = "Blood type cannot be null")
    Patient.BloodType bloodType,

    @Valid
    CreatePersonRequest person,

    @Valid
    CreatePersonRequest emergencyContact
) {}
