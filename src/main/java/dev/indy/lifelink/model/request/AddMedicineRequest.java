package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.constraints.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddMedicineRequest(
    @NotBlank(groups = OnCreate.class, message = "Medicine name cannot be empty")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 100, message = "Medicine name cannot exceed 100 characters")
    @Pattern(
        groups = {OnCreate.class, OnUpdate.class},
        regexp = Util.GENERIC_NAME_REGEXP, message =
        "Medicine name can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces"
    )
    String name,

    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 1000, message = "Medicine notes cannot exceed 1000 characters")
    String notes
) {}
