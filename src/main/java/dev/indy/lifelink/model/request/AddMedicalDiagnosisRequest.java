package dev.indy.lifelink.model.request;

import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddMedicalDiagnosisRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "ICD code cannot be empty")
    @Pattern(regexp = "(?i)^[a-tv-z][a-z0-9]{2}(\\.[a-z0-9]{1,4})?$", message = "Invalid ICD code format")
    @Length(min = 3, max = 7, message = "ICD code must be between 3 and 8 characters")
    String icdCode,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Description cannot be empty")
    @Length(max = 1000, message = "Description cannot exceed 1000 characters")
    String description
) {}
