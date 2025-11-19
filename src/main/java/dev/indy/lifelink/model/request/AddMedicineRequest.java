package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddMedicineRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Medicine name cannot be empty")
    @Pattern(regexp = Util.GENERIC_NAME_REGEXP, message = "Medicine name can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces")
    @Length(max = 100, message = "Medicine name cannot exceed 100 characters")
    String name,

    @Length(max = 1000, message = "Medicine notes cannot exceed 1000 characters")
    String notes,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Dosage cannot be empty")
    @Pattern(regexp = Util.GENERIC_NAME_REGEXP, message = "Dosage can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces")
    @Length(max = 50, message = "Dosage cannot exceed 50 characters")
    String dosage,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Frequency cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9 ',/-]+$", message = "Frequency can only contain alphanumeric characters, apostrophes, commas, hyphens, slashes, and spaces")
    @Length(max = 50, message = "Frequency cannot exceed 50 characters")
    String frequency,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Start date cannot be empty")
    @Pattern(regexp = Util.DATE_REGEXP, message = "Start date must be in the format DD/MM/YYYY")
    String startDate,

    @Pattern(regexp = Util.DATE_REGEXP, message = "End date must be in the format DD/MM/YYYY")
    String endDate
) {}
