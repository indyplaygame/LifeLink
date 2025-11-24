package dev.indy.lifelink.model.request;

import dev.indy.lifelink.validation.constraints.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddMedicalDiagnosisRequest(
    @NotBlank(groups = OnCreate.class, message = "ICD code cannot be empty")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "(?i)^[a-tv-z][a-z0-9]{2}(\\.[a-z0-9]{1,4})?$", message = "Invalid ICD code format")
    @Length(groups = {OnCreate.class, OnUpdate.class}, min = 3, max = 7, message = "ICD code must be between 3 and 8 characters")
    String icdCode,

    @NotBlank(groups = OnCreate.class, message = "Description cannot be empty")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 1000, message = "Description cannot exceed 1000 characters")
    String description,

    @NotBlank(groups = OnCreate.class, message = "Diagnosis date cannot be empty")
    @ValidDate(groups = {OnCreate.class, OnUpdate.class}, message = "Diagnosis date must be in the format DD-MM-YYYY")
    String date
) {}
