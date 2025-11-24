package dev.indy.lifelink.model.request;

import dev.indy.lifelink.util.Util;
import dev.indy.lifelink.validation.constraints.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddChronicDiseaseRequest(
    @NotBlank(groups = OnCreate.class, message = "Disease name cannot be empty")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 100, message = "Disease name cannot exceed 100 characters")
    @Pattern(
        groups = {OnCreate.class, OnUpdate.class},
        regexp = Util.GENERIC_NAME_REGEXP,
        message = "Disease name can only contain alphanumeric characters, apostrophes, commas, hyphens, and spaces"
    )
    String name,

    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 2000, message = "Disease notes cannot exceed 2000 characters")
    String notes,

    @NotBlank(groups = OnCreate.class, message = "Diagnosis date cannot be empty")
    @ValidDate(groups = {OnCreate.class, OnUpdate.class}, message = "Diagnosis date must be in the format DD-MM-YYYY")
    String diagnosisDate
) {}
