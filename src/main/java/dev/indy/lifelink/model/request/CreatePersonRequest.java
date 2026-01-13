package dev.indy.lifelink.model.request;

import dev.indy.lifelink.model.Person;
import dev.indy.lifelink.util.Util;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record CreatePersonRequest(
    @NotBlank(groups = OnCreate.class, message = "First name cannot be empty")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = Util.ALPHABETIC_REGEXP, message = "First name can only contain alphabetic characters")
    @Length(groups = {OnCreate.class, OnUpdate.class}, min = 1, max = 50, message = "First name must be up to 50 characters")
    String firstName,

    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = Util.ALPHABETIC_REGEXP, message = "Middle name can only contain alphabetic characters")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 50, message = "Middle name must be up to 50 characters")
    String middleName,

    @NotBlank(groups = OnCreate.class, message = "Last name cannot be empty")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = Util.ALPHABETIC_REGEXP, message = "Last name can only contain alphabetic characters")
    @Length(groups = {OnCreate.class, OnUpdate.class}, min = 1, max = 50, message = "Last name must be up to 50 characters")
    String lastName,

    @NotBlank(groups = OnCreate.class, message = "Email cannot be empty")
    @Email(groups = {OnCreate.class, OnUpdate.class}, message = "Invalid email format")
    @Length(groups = {OnCreate.class, OnUpdate.class}, min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    String email,

    @NotBlank(groups = OnCreate.class, message = "Phone number cannot be empty")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "^\\+?[1-9](?:[ -]?\\(?\\d\\)?){6,14}$", message = "Phone number format is invalid")
    String phoneNumber,

    @NotNull(groups = OnCreate.class, message = "Gender cannot be null")
    Person.Gender gender,

    @Validated
    CreateAddressRequest address
) {}