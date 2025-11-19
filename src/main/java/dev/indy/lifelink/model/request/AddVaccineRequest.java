package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddVaccineRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Vaccine name cannot be empty")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 100, message = "Vaccine name cannot exceed 100 characters")
    @Pattern(
        groups = {OnCreate.class, OnUpdate.class},
        regexp = Util.GENERIC_NAME_REGEXP,
        message = "Vaccine name can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces"
    )
    String name
) {}
