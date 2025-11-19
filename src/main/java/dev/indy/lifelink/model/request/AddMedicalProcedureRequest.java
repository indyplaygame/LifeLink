package dev.indy.lifelink.model.request;

import dev.indy.lifelink.validation.constraints.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import static dev.indy.lifelink.validation.ValidationGroups.*;

public record AddMedicalProcedureRequest(
    @NotBlank(groups = OnCreate.class, message = "CPT code cannot be empty")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "(?i)^[0-9]{4}[0-9a-z]$", message = "CPT code must be a 5-character alphanumeric code")
    @Length(groups = {OnCreate.class, OnUpdate.class}, min = 5, max = 5, message = "CPT code must be exactly 5 characters long")
    String cptCode,

    @NotBlank(groups = OnCreate.class, message = "Procedure description cannot be empty")
    @Length(groups = {OnCreate.class, OnUpdate.class}, max = 1000, message = "Procedure description cannot exceed 1000 characters")
    String procedureDescription,

    @NotBlank(groups = OnCreate.class, message = "Procedure date cannot be empty")
    @ValidDate(groups = {OnCreate.class, OnUpdate.class}, message = "Diagnosis date must be in the format DD-MM-YYYY")
    String procedureDate
) {}
