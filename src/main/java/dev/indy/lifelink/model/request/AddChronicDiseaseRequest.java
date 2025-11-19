package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddChronicDiseaseRequest(
    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Disease name cannot be empty")
    @Pattern(regexp = Util.GENERIC_NAME_REGEXP, message = "Disease name can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces")
    @Length(max = 100, message = "Disease name cannot exceed 100 characters")
    String name,

    @Length(max = 2000, message = "Disease notes cannot exceed 2000 characters")
    String notes,

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "Diagnosis date cannot be empty")
    @Pattern(regexp = Util.DATE_REGEXP, message = "Diagnosis date must be in the format DD/MM/YYYY")
    String diagnosisDate
) {}
