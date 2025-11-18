package dev.indy.lifelink.model.request;

import dev.indy.lifelink.model.Person;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

public record CreatePersonRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "First name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name can only contain alphabetic characters")
    @Length(min = 1, max = 50, message = "First name must be up to 50 characters")
    String firstName,

    @Pattern(groups = ValidationGroups.OnCreate.class, regexp = "^[a-zA-Z]+$", message = "Middle name can only contain alphabetic characters")
    @Length(max = 50, message = "Middle name must be up to 50 characters")
    String middleName,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Last name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name can only contain alphabetic characters")
    @Length(min = 1, max = 50, message = "Last name must be up to 50 characters")
    String lastName,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+?[1-9](?:[ -]?\\(?\\d\\)?){6,14}$", message = "Phone number format is invalid")
    String phoneNumber,

    Person.Gender gender,

    @Validated
    CreateAddressRequest address
) {}